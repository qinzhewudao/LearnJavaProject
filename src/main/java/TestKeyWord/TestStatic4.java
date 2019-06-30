package TestKeyWord;

import java.lang.reflect.Field;

/**
 * created at 2019/5/18
 *
 * @author sheyang
 */
public class TestStatic4 {

    static {
        System.out.println("test static 1");
    }

    static {
        System.out.println("test static 2");
    }

    public static void testReflection() throws Exception {

        //创建字符串"Hello World"， 并赋给引用s
        String s = "Hello World";

        //Hello World
        System.out.println("s = " + s);

        //获取String类中的value字段
        Field valueFieldOfString = String.class.getDeclaredField("value");

        //改变value属性的访问权限
        valueFieldOfString.setAccessible(true);

        //获取s对象上的value属性的值
        char[] value = (char[]) valueFieldOfString.get(s);

        //改变value所引用的数组中的第5个字符
        value[5] = '_';

        //Hello_World
        System.out.println("s = " + s);
    }

    public static void main(String[] args) {
        System.out.println("main");

        try {
            testReflection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //输出false，因为s2在堆上，而s2.intern返回常量池中的对象
        String s2 = new String("HelloWorld");
        System.out.println(s2 == s2.intern());
    }
}
