package TestProxy;

/**
 * 静态代理
 * author sheyang
 * created at 2018/10/23
 */

/**
 * 可以看到，SubjectProxy实现了Subject接口（和RealSubject实现相同接口），
 * 并持有的是Subject接口类型的引用。这样调用的依然是doSomething方法，只是
 * 实例化对象的过程改变了，结果来看，代理类SubjectProxy可以自动为我们加上了
 * before和after等我们需要的动作。
 * <p>
 * 如果将来需要实现一个新的接口，就需要在代理类里再写该接口的实现方法，对导致
 * 代理类的代码变得臃肿；另一方面，当需要改变抽象角色接口时，无疑真实角色和
 * 代理角色也需要改变。
 */
interface Subject//抽象角色
{
    void doSomething();
}

class RealSubject implements Subject//真实角色
{
    public void doSomething() {
        System.out.println("call doSomething(),I'm doing my work now。");
    }
}

class SubjectProxy implements Subject//代理角色
{
    //代理模式的作用是：为其他对象提供一种代理以控制对这个对象的访问。
    Subject subImpl = new RealSubject();

    public void doSomething() {
        System.out.println("before"); //调用目标对象之前可以做相关操作
        subImpl.doSomething();
        System.out.println("after");//调用目标对象之后可以做相关操作
    }
}

public class TestProxy {
    public static void main(String[] args) {
        Subject sub = new SubjectProxy();
        sub.doSomething();
    }
}
