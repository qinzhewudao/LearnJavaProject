package TestGenerics;

/**
 * author sheyang
 * created at 2018/9/3
 */

//此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型
//在实例化泛型类时，必须指定T的具体类型
public class Generic<T> {
    //key这个成员变量的类型为T,T的类型由外部指定
    private T key;

    public Generic(T key) { //泛型构造方法形参key的类型也为T，T的类型由外部指定
        this.key = key;
    }

    public static void showKeyValue(Generic<Number> obj) {
        System.out.println("泛型测试 key value is " + obj.getKey());
    }

    public static void showKeyValue1(Generic<?> obj) {
        System.out.println("泛型测试 key value is " + obj.getKey());
    }

    /*回到上面的例子，如何解决上面的问题？总不能为了定义一个新的方法来处理
    Generic<Integer>类型的类，这显然与java中的多台理念相违背。因此我们需
    要一个在逻辑上可以表示同时是Generic<Integer>和Generic<Number>父类的
    引用类型。由此类型通配符应运而生。
    我们可以将上面的方法改一下：*/

    public static void main(String[] args) {
        //泛型的类型参数只能是类类型（包括自定义类），不能是简单类型
        //传入的实参类型需与泛型的类型参数类型相同，即为Integer.
        Generic<Integer> genericInteger = new Generic<>(123456);

        //传入的实参类型需与泛型的类型参数类型相同，即为String.
        Generic<String> genericString = new Generic<>("key_value");
        System.out.println("泛型测试 key is " + genericInteger.getKey());
        System.out.println("泛型测试 key is " + genericString.getKey());

        // 定义的泛型类，就一定要传入泛型类型实参么？并不是这样，在使用泛型的时候如果
        // 传入泛型实参，则会根据传入的泛型实参做相应的限制，此时泛型才会起到本应起到
        // 的限制作用。如果不传入泛型类型实参的话，在泛型类中使用泛型的方法或成员变量
        // 定义的类型可以为任何的类型。
        Generic generic = new Generic("111111");
        Generic generic1 = new Generic(4444);
        Generic generic2 = new Generic(55.55);
        Generic generic3 = new Generic(false);

        System.out.println("泛型测试 key is " + generic.getKey());
        System.out.println("泛型测试 key is " + generic1.getKey());
        System.out.println("泛型测试 key is " + generic2.getKey());
        System.out.println("泛型测试 key is " + generic3.getKey());

        Generic<Integer> gInteger = new Generic<Integer>(123);
        Generic<Number> gNumber = new Generic<Number>(456);

        showKeyValue1(gNumber);

// showKeyValue这个方法编译器会为我们报错：Generic<java.lang.Integer>
// cannot be applied to Generic<java.lang.Number>
// showKeyValue(gInteger);
    }
    /*
    类型通配符一般是使用？代替具体的类型实参，注意了，此处’？’是类型实参，而不是类型形参 。
    重要说三遍！此处’？’是类型实参，而不是类型形参 ！
    此处’？’是类型实参，而不是类型形参 ！再直白点的意思就是，
    此处的？和Number、String、Integer一样都是一种实际的类型，
    可以把？看成所有类型的父类。是一种真实的类型。
     */

    public T getKey() { //泛型方法getKey的返回值类型为T，T的类型由外部指定
        return key;
    }

    /**
     * 泛型方法的基本介绍
     *
     * @param tClass 传入的泛型实参
     * @return T 返回值为T类型
     * 说明：
     * 1）public 与 返回值中间<T>非常重要，可以理解为声明此方法为泛型方法。
     * 2）只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是泛型方法。
     * 3）<T>表明该方法将使用泛型类型T，此时才可以在方法中使用泛型类型T。
     * 4）与泛型类的定义一样，此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型。
     */
    public <T> T genericMethod(Class<T> tClass) throws InstantiationException,
            IllegalAccessException {
        T instance = tClass.newInstance();
        return instance;
    }

}
