package TestString;

/**
 * author sheyang
 * created at 2018/7/26
 */
public class TestString1 {

    public static void main(String[] args){
        //String不可被修改，只能被赋值一次
        String s1 = "abcedf";
        String s2 = s1.substring(2,4);
        System.out.println(Integer.toHexString(s1.hashCode()));
        System.out.println(Integer.toHexString(s2.hashCode()));

        //编译器做了优化,直接在编译的时候将字符串进行拼接
        String s3 = "hello" + " java";
        String s4 = "hello java";
        System.out.println(s3 == s4);

        //编译的时候不知道变量中存储的是什么,所以没办法在编译的时候优化

        String s5 = "hello";
        String s6 = " java";
        String s7 = s5 + s6;
        System.out.println(s4 == s7);


        /*

        这里面涉及到的是String.intern方法的使用。在String类中，intern方法是一个本地方法，
        在JAVA SE6之前，intern方法会在运行时常量池中查找是否存在内容相同的字符串，如果存在则返回指
        向该字符串的引用，如果不存在，则会将该字符串入池，并返回一个指向该字符串的引用。因此，a和d指向的是同一个对象。
         */
//        String a = "hello";
//        String b =  new String("hello");
//        String c =  new String("hello");
//        String d = b.intern();
//
//        System.out.println(a==b);
//        System.out.println(b==c);
//        System.out.println(b==d);
//        System.out.println(a==d);

        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        Long h = 2L;

        System.out.println(c==d);
        System.out.println(e==f);
        System.out.println(c==(a+b));
        System.out.println(c.equals(a+b));
        System.out.println(g==(a+b));
        System.out.println(g.equals(a+b));
        System.out.println(g.equals(a+h));

    }

}
