package cache;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author sheyang
 * @date 2019/7/24 8:51
 */
public class CglibProxy implements MethodInterceptor {

    private String name;

    public CglibProxy(String name) {
        super();
        this.name = name;
    }

    public Object newInstall(Object object) {
        return Enhancer.create(object.getClass(), this);
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("我开始代理了方法:" + method.getName() + " 参数:" + Arrays.toString(objects));
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("我结束代理了方法:" + method.getName() + " 结果:" + result);
        return result;
    }

}