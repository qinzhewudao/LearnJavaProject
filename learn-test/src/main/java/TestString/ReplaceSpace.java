package TestString;

/**
 * created at 2019/3/25
 *
 * @author sheyang
 */
public class ReplaceSpace {

    private static String replaceSpace(StringBuffer str) {
        int p1 = str.length() - 1;
        //现根据空格的数量扩展好字符串的长度
        for (int i = 0; i <= p1; i++) {
            if (str.charAt(i) == ' ') {
                str.append("  ");
            }
        }

        int p2 = str.length() - 1;
        //遇见空格就替换为%20，字符就替换为字符
        while (p1 >= 0 && p2 > p1) {
            char c = str.charAt(p1--);
            if (c == ' ') {
                str.setCharAt(p2--, '0');
                str.setCharAt(p2--, '2');
                str.setCharAt(p2--, '%');
            } else {
                str.setCharAt(p2--, c);
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        String text = "2b 3b 4b";
        StringBuffer input = new StringBuffer(text);
        System.out.println(replaceSpace(input));
    }

}
