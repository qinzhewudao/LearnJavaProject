package TestString;

/**
 * author sheyang
 * created at 2018/7/31
 */
public class TestParamemterTransferMode1 {
    //基本类型的参数传递

    public static void testBasicType(int m) {

        System.out.println("刚传递进来时m=" + m);//m=50

        m = 100;

        System.out.println("传递进来之后m=" + m);//m=100

    }



    //参数为对象，不改变引用的值 ？？？？？？

    public static void add(StringBuffer s) {

        s.append("_add");

    }



    //参数为对象，改变引用的值 ？？？？？

    public static void changeRef(StringBuffer s) {

        s = new StringBuffer("Java");

    }



    public static void main(String[] args) {

        int i = 50;

        testBasicType(i);

        System.out.println("在main函数里调用函数后m="+i);//i=50

        StringBuffer sMain = new StringBuffer("init");

        System.out.println("sMain=" + sMain.toString());//sMain=init

        add(sMain);

        System.out.println("sMain=" + sMain.toString());//sMain=init_add

        changeRef(sMain);

        System.out.println("sMain=" + sMain.toString());//sMain=init_add

//        以上程序的允许结果显示出，testBasicType方法的参数是基本类型，尽管参数m的值发生改变，但并不影响i。
//
//        add方法的参数是一个对象，当把sMain传给参数s时，s得到的是sMain的拷贝，所以s和sMain指向同一个对象，因此，使用s操作影响的其实就是sMain指向的对象，故调用add方法后，sMain指向的对象的内容发生了改变。
//
//        在changeRef方法中，参数也是对象，当把sMain传给参数s时，s得到的是sMain的拷贝，但与add方法不同的是，在方法体内改变了s指向的对象（也就是s指向了别的对象,牵着气球的绳子换气球了），给s重新赋值后，s与sMain已经毫无关联，它和sMain指向了不同的对象，所以不管对s做什么操作，都不会影响sMain指向的对象，故调用changeRef方法前后sMain指向的对象内容并未发生改变。
//
//        对于add方法的调用结果，可能很多人会有这种感觉：这不明明是按引用传递吗？对于这种问题，还是套用Bruce Eckel的话：这依赖于你如何看待引用，最终你会明白，这个争论并没那么重要。真正重要的是，你要理解，传引用使得（调用者的）对象的修改变得不可预期。

    }
}
