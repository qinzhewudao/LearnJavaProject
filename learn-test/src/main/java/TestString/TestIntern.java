package TestString;

/**
 * @author sheyang
 * @date 2021/6/28 8:09 下午
 */
public class TestIntern {

    public static void main(String[] args) {
        String str6 = new String("计算机") + "软件";

        //str1.intern 方法区不存在 将str1地址添加进去了 str1 堆上对象 true
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        String str3 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str3);

        //openjdk 已经被放进去了 系统做的 str2 堆上对象 地址不一
        String str2 = new StringBuilder("open").append("jdk").toString();
        System.out.println(str2.intern() == str2);

        String str4 = new StringBuilder("ja").append("va").toString();
        System.out.println(str4.intern() == str4);
    }

}
