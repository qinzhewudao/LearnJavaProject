package TestChar;

/**
 * author sheyang
 * created at 2018/9/3
 */
public class TestUnicode {
    public static void main(String[] args) {
//4A表示Unicode字符U+004A 拉丁字符J，可以写成\u004A（使用\\u前缀)
//
//\n,\r,\t,\b代表换行、回车、tab、退格。
//
//在不同系统下换行符不同，window下是/r/n。Linux下是/n。可以使用

        //从当前系统中获取换行符，默认是"\n"
        String lineSeparator = System.getProperty("line.separator", "\n");
        System.out.println(lineSeparator);
        //String[] arr = fileContet.splite(lineSeparator );

        System.out.println(-17 % 12);


        //java中，只能用boolean来作为判断表达式。不像C++数字 非0便是false
//        if(1){
//
//        }
    }

}
