package util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: sheyang
 * @date: 2020/3/11 10:14 上午
 */
@Slf4j
public class BuilderProtoBuf {

    public static void readFiles(File file, StringBuffer sb) throws Exception {
        if (file.isFile()) {
            if (file.getName().endsWith(".md")) {
                log.info(file.getAbsolutePath());
                readFile(file, sb);
            }
        } else {
            for (File file2 : file.listFiles()) {
                readFiles(file2, sb);
            }
        }
    }

    public static void readFile(File readfile, StringBuffer sb) throws Exception {
        log.info(readfile.getAbsolutePath());
        InputStreamReader read = new InputStreamReader(new FileInputStream(readfile), "utf-8");//考虑到编码格式
        BufferedReader bufferedReader = new BufferedReader(read);
        String line;
        StringBuffer stringBuffer = new StringBuffer();
        while ((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line + "\n");
        }
        bufferedReader.close();
        read.close();

        //抽取内容
        Pattern p = Pattern.compile("(?<=<code>)[^<>]+(?=</code>)");//"(?<=<span\b[^>]*?>)[^<>]+(?=</span>)"
        Matcher m = p.matcher(stringBuffer.toString());

        while (m.find()) {
            sb.append(m.group());
            sb.append("\n");
        }
    }

    public static void buildProtoBuf(String filePath, String protocolAllInOnePath) throws Exception {

        StringBuffer sb = new StringBuffer();
        sb.append("//公共属性\n" +
                "syntax = \"proto3\";\n" +
                "package Message;\n" +
                "option java_package = \"com.yupaopao.platform.game.undercover.module.protocol\";\n" +
                "option optimize_for = SPEED;\n" +
                "option java_outer_classname = \"GameProtocol\";\n" +
                "option java_multiple_files = true;\n");

        log.info("------------------------read files start---------------------------");
        readFiles(new File(filePath), sb);
        log.info("------------------------read files end---------------------------");
        //写入文件
        writeToFile(sb.toString(), protocolAllInOnePath);

    }

    public static void writeToFile(String content, String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file, false);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);
        bw.close();
        fw.close();
    }

    public static String runShell(String shStr) throws Exception {
        log.info("shell:" + shStr);
        Process process;
        process = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", shStr});
        process.waitFor();
        BufferedReader read = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        StringBuilder result = new StringBuilder();
        while ((line = read.readLine()) != null) {
            result.append(line);
            log.info(line);
        }
        return result.toString();
    }

    public static void main(String[] args) throws Exception {
        String wikiGitUrl = "http://git.yupaopao.com/platform/game/game-msg-share.wiki.git";

        //注意 ：下面5行代码请不要修改
        final String rootPath = System.getProperty("user.dir");
        final String LocalPath = rootPath + "/game-gateway-proto";
        final String wikiLocalPath = LocalPath + "/game-msg-share.wiki";
        final String protocolAllInOnePath = wikiLocalPath + "/protocol/GameProtocol.proto";
        final String protocPath = rootPath + "/protoc";
        final String rmPath = rootPath + "/bxgame-undercover/bxgame-undercover-service-core/src/main/java/com/yupaopao/platform/game/undercover/module/protocol/";
        final String toPath = rootPath + "/bxgame-undercover-service-core/src/main/java/";

        // 只编译子目录的可以在这里指定
        final String subPath = "sswd/game";


        //拉取最新代码
        StringBuilder sb = new StringBuilder();
        sb.append("mkdir -p " + wikiLocalPath + "/protocol/java");
        sb.append(" && ");
        sb.append("cd " + LocalPath);
        sb.append(" && ");
        sb.append("rm -rf " + wikiLocalPath);
        sb.append(" && ");
        sb.append("git clone " + wikiGitUrl);
        sb.append(" && ");
        sb.append("cd " + wikiLocalPath);
        sb.append(" && ");
        sb.append("mkdir -p " + wikiLocalPath + "/protocol/java");
        sb.append(" && ");
        sb.append("ls");
        runShell(sb.toString());

        //拼接文件
        buildProtoBuf(wikiLocalPath + "/" + subPath, protocolAllInOnePath);

        //生成Java文件
        StringBuilder sb2 = new StringBuilder();
        sb2.append("\\cp -rf " + protocolAllInOnePath + " " + rootPath);
        sb2.append(" && ");
        sb2.append("cd " + wikiLocalPath + "/protocol");
        sb2.append(" && ");
        sb2.append("mkdir -p java");
        sb2.append(" && ");
        sb2.append(protocPath + " --java_out=./java GameProtocol.proto");
        runShell(sb2.toString());

        //复制java文件
        File file = new File(wikiLocalPath + "/protocol/java");
        if (!(file.exists() && file.isDirectory() && file.list().length > 0)) {
            return;
        }

        StringBuilder sb3 = new StringBuilder().append("cd " + wikiLocalPath + "/protocol/java");
        sb3.append(" && ");
        sb3.append("rm -rf " + rmPath);
        sb3.append(" && ");
        sb3.append("\\cp -rf * " + toPath);
        sb3.append(" && ");
        sb3.append("rm -rf " + LocalPath);
        runShell(sb3.toString());
        log.info(" success ");
    }

}
