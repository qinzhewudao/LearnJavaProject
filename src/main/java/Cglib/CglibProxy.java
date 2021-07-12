package Cglib;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author sheyang
 * @date 2021/7/9 11:17 上午
 */
@Slf4j
public class CglibProxy implements MethodInterceptor {

    private final String name;

    public CglibProxy(String name) {
        super();
        this.name = name;
    }

    public Object newInstall(Object object) {
        return Enhancer.create(object.getClass(), this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            return methodProxy.invokeSuper(o, objects);
        } catch (Throwable t) {
            throw t;
        } finally {
            log.info("method:{} costTime:{}", method.getName(), System.currentTimeMillis() - startTime);
        }
    }

}
