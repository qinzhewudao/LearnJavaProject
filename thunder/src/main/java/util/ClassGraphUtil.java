package util;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * 当出现 javassisst/bytecode/classfile 找不到的时候
 * 排除reflections下的org的javassist包
 * 使用javassist即可
 * <dependency>
 * <groupId>javassist</groupId>
 * <artifactId>javassist</artifactId>
 * <version>3.12.1.GA</version>
 * </dependency>
 *
 * <dependency>
 * <groupId>org.reflections</groupId>
 * <artifactId>reflections</artifactId>
 * <version>0.9.12</version>
 * <exclusions>
 * <exclusion>
 * <artifactId>javassist</artifactId>
 * <groupId>org.javassist</groupId>
 * </exclusion>
 * </exclusions>
 * </dependency>
 *
 * @author: sheyang
 * @date: 2020/2/26 4:32 下午
 */
public class ClassGraphUtil {

    public static List<Class<?>> scanClassesWithAno(String packageNames, Class<? extends Annotation> anoCls) {
        ScanResult scanResult = new ClassGraph()
                .enableAnnotationInfo()
                .whitelistPackages(packageNames)
                .scan();

        ClassInfoList classInfoList = scanResult.getClassesWithAnnotation(anoCls.getName());
        if (null == classInfoList) {
            return new ArrayList<>();
        }
        return classInfoList.loadClasses();
    }

}
