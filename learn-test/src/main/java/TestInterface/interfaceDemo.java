package TestInterface;

/**
 * created at 2019/8/1
 *
 * @author sheyang
 */
public interface interfaceDemo {

    /**
     * 接口的默认方法 可以重写
     */
    default void sayHelloWorld() {
        System.out.println("I'm default Hello World!");
    }

    /**
     * 接口的静态方法 可以通过接口名调用
     */
    static void showStatic() {
        System.out.println("I'm static Hello World!");
    }

    /**
     * 接口的普通方法 必须在实现类里实现
     */
    void sayHi();
}
