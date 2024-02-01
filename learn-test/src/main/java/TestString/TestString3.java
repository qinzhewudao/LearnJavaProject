package TestString;

/**
 * author sheyang
 * created at 2018/8/1
 */
public class TestString3 {
    private static int time = 50000;

    public static void main(String[] args) {
        testString();
        testStringBuffer();
        testStringBuilder();
        test1String();
        test2String();
        testOriginString();
        testOptimalString();
    }

    /*
    对于直接相加字符串，效率很高，因为在编译器便确定了它的值，也就是说形如"I"+"love"+"java"; 的字符串相加，在编译期间便被优化成了"Ilovejava"。这个可以用javap -c命令反编译生成的class文件进行验证。

　　对于间接相加（即包含字符串引用），形如s1+s2+s3; 效率要比直接相加低，因为在编译器不会对引用变量进行优化。


    StringBuilder > StringBuffer > String

　　当然这个是相对的，不一定在所有情况下都是这样。

　　比如String str = "hello"+ "world"的效率就比 StringBuilder st  = new StringBuilder().append("hello").append("world")要高。

　　因此，这三个类是各有利弊，应当根据不同的情况来进行选择使用：

　　当字符串相加操作或者改动较少的情况下，建议使用 String str="hello"这种形式；

　　当字符串相加操作较多的情况下，建议使用StringBuilder，如果采用了多线程，则使用StringBuffer。
     */

    private static void testString() {
        String s = "";
        long begin = System.currentTimeMillis();
        for (int i = 0; i < time; i++) {
            s += "java";
        }
        long over = System.currentTimeMillis();
        System.out.println("操作" + s.getClass().getName() + "类型使用的时间为：" + (over - begin) + "毫秒");
    }

    private static void testStringBuffer() {
        StringBuffer sb = new StringBuffer();
        long begin = System.currentTimeMillis();
        for (int i = 0; i < time; i++) {
            sb.append("java");
        }
        long over = System.currentTimeMillis();
        System.out.println("操作" + sb.getClass().getName() + "类型使用的时间为：" + (over - begin) + "毫秒");
    }

    public static void testStringBuilder() {
        StringBuilder sb = new StringBuilder();
        long begin = System.currentTimeMillis();
        for (int i = 0; i < time; i++) {
            sb.append("java");
        }
        long over = System.currentTimeMillis();
        System.out.println("操作" + sb.getClass().getName() + "类型使用的时间为：" + (over - begin) + "毫秒");
    }

    private static void test1String() {
        long begin = System.currentTimeMillis();
        for (int i = 0; i < time; i++) {
            String s = "I" + "love" + "java";
        }
        long over = System.currentTimeMillis();
        System.out.println("字符串直接相加操作：" + (over - begin) + "毫秒");
    }

    private static void test2String() {
        String s1 = "I";
        String s2 = "love";
        String s3 = "java";
        long begin = System.currentTimeMillis();
        for (int i = 0; i < time; i++) {
            String s = s1 + s2 + s3;
        }
        long over = System.currentTimeMillis();
        System.out.println("字符串间接相加操作：" + (over - begin) + "毫秒");
    }

    private static void testOriginString() {
        String s = " ";
        long begin = System.currentTimeMillis();
        for (int i = 0; i < time; i++) {
            s += "java.";
        }
        long over = System.currentTimeMillis();
        System.out.println("操作" + s.getClass().getName() + "类型使用的时间为：" + (over - begin) + "毫秒");
    }

    private static void testOptimalString() {
        String s = " ";
        long begin = System.currentTimeMillis();
        for (int i = 0; i < time; i++) {
            StringBuilder sb = new StringBuilder(s);
            sb.append("java.");
            s = sb.toString();
        }
        long over = System.currentTimeMillis();
        System.out.println("模拟JVM优化操作的时间为：" + (over - begin) + "毫秒");
    }

}
