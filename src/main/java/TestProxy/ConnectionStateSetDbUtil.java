package TestProxy;

/**
 * 添加设置lettuce db功能
 *
 * @author sheyang
 * @date 2021/7/9 4:49 下午
 * <p>
 * 添加设置db功能
 */

import io.lettuce.core.ConnectionState;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.loading.ClassInjector;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.implementation.bind.annotation.This;
import net.bytebuddy.matcher.ElementMatchers;

import java.io.File;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.util.Collections;
import java.util.concurrent.Callable;

/**
 * 设置db
 */
public class ConnectionStateSetDbUtil {
    public static final String CLUSTER_DB_SPLIT = "cluster-db####";

    public static void premain(String arg, Instrumentation inst) {
        try {
            File temp = Files.createTempDirectory("tmp-xxx-9527").toFile();
            ClassInjector.UsingInstrumentation.of(temp, ClassInjector.UsingInstrumentation.Target.BOOTSTRAP, inst).inject(Collections.singletonMap(
                    new TypeDescription.ForLoadedType(LettuceConnectionStateInterceptor.class),
                    ClassFileLocator.ForClassLoader.read(LettuceConnectionStateInterceptor.class)));
            new AgentBuilder.Default()
                    .ignore(ElementMatchers.not(ElementMatchers.named("io.lettuce.core.ConnectionState")))
                    .with(new AgentBuilder.InjectionStrategy.UsingInstrumentation(inst, temp))
                    .type(ElementMatchers.nameEndsWith("io.lettuce.core.ConnectionState"))
                    .transform((builder, typeDescription, classLoader, module) -> builder.method(ElementMatchers.named("apply")).intercept(MethodDelegation.to(LettuceConnectionStateInterceptor.class))).installOn(inst);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class LettuceConnectionStateInterceptor {
        public static void intercept(@SuperCall Callable<?> zuper, @This Object o) throws Exception {
            zuper.call();

            ConnectionState state = (ConnectionState) o;
            System.out.println("################################=" + state);
            Field fieldDb = ConnectionState.class.getDeclaredField("db");
            fieldDb.setAccessible(true);
            int db = (int) fieldDb.get(state);
            System.out.println(db);

            if (db == 0) {
                Field fieldClientName = ConnectionState.class.getDeclaredField("clientName");
                fieldClientName.setAccessible(true);
                String clientName = (String) fieldClientName.get(state);
                if (clientName.contains(CLUSTER_DB_SPLIT)) {
                    String dbStr = clientName.substring(clientName.indexOf(CLUSTER_DB_SPLIT) + CLUSTER_DB_SPLIT.length());
                    int dbIndex = Integer.parseInt(dbStr);
                    System.out.println("dbIndex:" + dbIndex);
                    fieldDb.setInt(state, dbIndex);
                    System.out.println(fieldDb.get(state));
                }
            }
        }
    }
}


