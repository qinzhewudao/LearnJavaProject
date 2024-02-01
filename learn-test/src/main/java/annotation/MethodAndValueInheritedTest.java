package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author sheyang
 * @date 2021/6/21 11:02 上午
 */
public class MethodAndValueInheritedTest {

    @Target(value = {ElementType.METHOD, ElementType.FIELD})
    @Retention(value = RetentionPolicy.RUNTIME)
    @interface DESC {
        String value() default "";
    }

    class SuperClass {
        @DESC("父类方法foo")
        public void foo() {
        }

        @DESC("父类方法bar")
        public void bar() {
        }

        @DESC("父类的属性1")
        public String firstValue;

        @DESC("父类的属性2")
        public String secondValue;
    }

    class ChildClass extends SuperClass {

        @DESC("子类的属性2")
        public String secondValue;

        @Override
        public void foo() {
            super.foo();
        }
    }

    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException {
        Method foo = ChildClass.class.getMethod("foo");
        System.out.println(Arrays.toString(foo.getAnnotations()));
        // output: []
        // 子类ChildClass重写了父类方法foo,并且@Override注解只在源码阶段保留，所以没有任何注解

        Method bar = ChildClass.class.getMethod("bar");
        System.out.println(Arrays.toString(bar.getAnnotations()));
        // output: [@annotations.InheritedTest$DESC(value=父类方法bar)]
        // bar方法未被子类重写，从父类继承到了原本注解

        Field field1 = ChildClass.class.getField("firstValue");
        System.out.println(Arrays.toString(field1.getAnnotations()));
        // output: [@annotations.InheritedTest$DESC(value=父类的属性1)]
        // 解释同上

        Field field2 = ChildClass.class.getField("secondValue");
        System.out.println(Arrays.toString(field2.getAnnotations()));
        // output: [@annotations.InheritedTest$DESC(value=子类的属性2)]
        // 解释同上

        Field field3 = SuperClass.class.getField("secondValue");
        System.out.println(Arrays.toString(field3.getAnnotations()));
        // output: [@annotations.InheritedTest$DESC(value=父类的属性2)]
        // 解释同上
    }

}
