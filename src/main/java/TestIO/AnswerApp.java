package TestIO;

import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author sheyang
 * @description
 * @date 2019/9/8
 * @time 下午8:04
 */
public class AnswerApp {

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();

        statistics();

        long endTime = System.currentTimeMillis();

        System.out.println("execute time " + (endTime - startTime) + " ms.");
    }


    public static void statistics() throws IOException {
        System.out.println("请输入搜索关键词(多个关键词以英文逗号隔开):");
        Scanner scanner = new Scanner(System.in);
        String keyWord = scanner.nextLine();

        String[] keyWords = keyWord.split(",");

        File file = new File("C:\\Users\\answer\\work detail.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        // 直接读取文本到集合中. commons.io 包
//		List<String> datas = FileUtils.readLines(file, Charsets.UTF_8);

        String line;

        // 定义一个结果存储容器, key 为 关键词, value 为关键词出现的次数
        HashMap<String, Integer> resultMap = new HashMap<>();

        while ((line = bufferedReader.readLine()) != null) {
            for (String kw : keyWords) {
                kw = kw.trim();

                if (StringUtils.isEmpty(kw)) {
                    continue;
                }

                int count = line.split(kw).length - 1;

                // 如果是以 关键词 结尾的, 此处会统计不到, 因此要单独判断
                if (count == 0) {
                    if (line.endsWith(kw)) {
                        count = 1;
                    }
                }

                if (resultMap.containsKey(kw)) {
                    resultMap.put(kw, resultMap.get(kw) + count);
                } else {
                    resultMap.put(kw, count);
                }
            }
        }

        List<Map.Entry<String, Integer>> list = Lists.newArrayList(resultMap.entrySet());

        // 自定义比较器, 根据 list 中元素的 value 值进行 从大到小 排序
        list.sort((o1, o2) -> o2.getValue() - o1.getValue());

        System.out.println();
        System.out.println("统计结果: ");
        for (Map.Entry entry : list) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }

}

