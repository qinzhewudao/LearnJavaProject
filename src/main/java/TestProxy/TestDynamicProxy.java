package TestProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * author sheyang
 * created at 2018/10/23
 */

/**
 * 　在调用过程中使用了通用的代理类包装了DynamicRealSubject实例，
 * 然后调用了Jdk的代理工厂方法实例化了一个具体的代理类。最后调用
 * 代理的doSomething方法，还有附加的before、after方法可以被任意复用
 * （只要我们在调用代码处使用这个通用代理类去包装任意想要需要包装的被代理类即可）。
 * 当接口改变的时候，虽然被代理类需要改变，但是我们的代理类却不用改变了。
 * 这个调用足够灵活，可以动态生成一个具体的代理类，而不用自己显示的创建一个实现具体接口的代理类。
 */

interface DynamicSubject//抽象角色
{
    void doSomething();
}

class DynamicRealSubject implements DynamicSubject//真实角色
{
    public void doSomething() {
        System.out.println("call doSomething(),I'm doing my work now。");
    }
}

class DynamicProxyHandler implements InvocationHandler//代理角色
{
    private Object tar;

    //绑定委托对象，并返回代理类
    Object bind(Object tar) {
        this.tar = tar;
        //绑定该类实现的所有接口，取得代理类
        return Proxy.newProxyInstance(tar.getClass().getClassLoader(),
                tar.getClass().getInterfaces(),
                this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable//不依赖具体接口实现
    {
        Object result;//被代理的类型为Object基类
        //这里就可以进行所谓的AOP编程了
        //在调用具体函数方法前，执行功能处理
        result = method.invoke(tar, args);
        //在调用具体函数方法后，执行功能处理
        return result;
    }
}

public class TestDynamicProxy {
    public static void main(String args[]) {
        DynamicProxyHandler proxy = new DynamicProxyHandler();
        //绑定该类实现的所有接口
        DynamicSubject sub = (DynamicSubject) proxy.bind(new DynamicRealSubject());
        sub.doSomething();
    }
}
