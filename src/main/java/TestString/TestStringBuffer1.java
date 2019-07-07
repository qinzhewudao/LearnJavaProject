package TestString;

/**
 * author sheyang
 * created at 2018/7/31
 */
public class TestStringBuffer1 {
    public static void main(String[] args) {
        /*
        操作字符串的类有：String、StringBuffer、StringBuilder。

String 和 StringBuffer、StringBuilder 的区别在于 String 声明的是不可变的对象，每次操作都会生成新的 String 对象，
然后将指针指向新的 String 对象，而 StringBuffer、StringBuilder 可以在原有对象的基础上进行操作，
所以在经常改变字符串内容的情况下最好不要使用 String。

StringBuffer 和 StringBuilder 最大的区别在于，StringBuffer 是线程安全的，而 StringBuilder 是非线程安全的，
但 StringBuilder 的性能却高于 StringBuffer，所以在单线程环境下推荐使用 StringBuilder，多线程环境下推荐使用 StringBuffer。
         */
        StringBuffer s;
        s = new StringBuffer("java");
        //两个引用指向同一个对象，地址相同，类似于c++的指针
        //这样，对其中一个引用进行操作，另一个引用得到的结果必然也会随之改变
        //s1和s只是两个引用，它们只是操纵杆而已，它们指向同一个对象，操纵的也
        // 是同一个对象，通过它们得到的是同一个对象的内容。这就像汽车的刹车和油门，
        // 它们操纵的都是车速，假如汽车开始的速度是80，然后你踩了一次油门，汽车加速了，
        // 假如车速升到了120，然后你踩一下刹车，此时车速是从120开始下降的，假如下降到60，
        // 再踩一次油门，车速则从60开始上升，而不是从第一次踩油门后的120开始。也就是说车
        // 速同时受油门和刹车影响，它们的影响是累积起来的，而不是各自独立（除非刹车和油门
        // 不在一辆车上）。所以，在上面的程序中，不管使用s1还是s操纵对象，它们对对象的影
        // 响也是累积起来的（更多的引用同理）。
        StringBuffer s1 = s;

        s1.append(" world");
        System.out.println("s1=" + s1.toString());
        System.out.println("s=" + s.toString());
    }


}
