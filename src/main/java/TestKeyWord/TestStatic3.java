package TestKeyWord;

/**
 * author sheyang
 * created at 2018/8/3
 */
public class TestStatic3 {
    public static void main(String[] args) {

        Bread bread1 = new Bread();
        Bread bread2 = new Bread();

        System.out.println("###################");

        new Meal();
    }
}

/*
static块只会被初始化一次
 */
class Bread {
    static{
        System.out.println("Bread is loaded");
    }
    Bread() {
        System.out.println("bread");
    }
}

/*
在生成对象的过程中，会先初始化对象的成员变量，然后再执行构造器。也就是说类中的变量会在任何方法（包括构造器）
调用之前得到初始化，即使变量散步于方法定义之间。


 */
class Meal {

    Meal() {
        System.out.println("meal");
    }

    Bread bread = new Bread();
}