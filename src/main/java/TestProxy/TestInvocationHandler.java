package TestProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * author sheyang
 * created at 2018/10/23
 */
public class TestInvocationHandler {
    public static void main(String[] args){
        Object[] values = new Object[10];
        for(int i=0;i<values.length;i++){
            Object value = i;
            values[i] = Proxy.newProxyInstance(null,
                    value.getClass().getInterfaces(),
                    //调用处理器的lambda表达式
                    (Object proxy, Method m , Object[] mArgs)->{
                        System.out.println(value+m.getName()+Arrays.toString(mArgs));
                        return m.invoke(value,mArgs);
                    });
            System.out.println(value);
        }

        for(int i=0;i<values.length;i++){
            Object value = LocalDateTime.now();
            values[i] = value;
            System.out.println(value);
        }
    }

}


