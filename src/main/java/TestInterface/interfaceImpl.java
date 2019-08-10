package TestInterface;

/**
 * created at 2019/8/1
 *
 * @author sheyang
 */
public class interfaceImpl implements interfaceDemo {
    /**
     * 接口的普通方法 必须在实现类里实现
     */
    @Override
    public void sayHi() {
        System.out.println("I'm implement Hello World!");
    }

    public static void main(String[] args){

        interfaceDemo.showStatic();
        interfaceImpl interfaceImpl = new interfaceImpl();
        interfaceImpl.sayHi();
        interfaceImpl.sayHelloWorld();
    }

}
