package annotation;

import java.lang.annotation.*;
import java.util.Arrays;

/**
 * @author sheyang
 * @date 2021/6/21 10:40 上午
 */
public class ClassInheritedTest {
    @Target(value = ElementType.TYPE)
    @Retention(value = RetentionPolicy.RUNTIME)
    @Inherited // 声明注解具有继承性
    @interface AInherited {
        String value() default "";
    }

    @Target(value = ElementType.TYPE)
    @Retention(value = RetentionPolicy.RUNTIME)
    @Inherited // 声明注解具有继承性
    @interface BInherited {
        String value() default "";
    }

    @Target(value = ElementType.TYPE)
    @Retention(value = RetentionPolicy.RUNTIME)
            // 未声明注解具有继承性
    @interface CInherited {
        String value() default "";
    }

    @AInherited("父类的AInherited")
    @BInherited("父类的BInherited")
    @CInherited("父类的CInherited")
    class SuperClass {
    }

    @BInherited("子类的BInherited")
    class ChildClass extends SuperClass {
    }

    public static void main(String[] args) {
        Annotation[] annotations = ChildClass.class.getAnnotations();
        System.out.println(Arrays.toString(annotations));
        // output: [@annotations.InheritedTest1$AInherited(value=父类的AInherited), @annotations.InheritedTest1$BInherited(value=子类的BInherited)]
    }
}
