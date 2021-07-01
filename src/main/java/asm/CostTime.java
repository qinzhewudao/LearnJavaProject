package asm;

import org.objectweb.asm.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * @author sheyang
 * @date 2021/7/1 2:09 下午
 */
public class CostTime {

    public static void main(String[] args) {
        redefinePersonClass();
    }

    private static void redefinePersonClass() {
        String className = "asm.HelloWorld";
        try {
            InputStream inputStream = new FileInputStream("/Users/sheyang/IdeaProjects/LearnJavaProject/src/main/java/asm/HelloWorld.java");
            // 1. 创建 ClassReader 读入 .class 文件到内存中
            ClassReader reader = new ClassReader(inputStream);
            // 2. 创建 ClassWriter 对象，将操作之后的字节码的字节数组回写
            ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_MAXS);
            // 3. 创建自定义的 ClassVisitor 对象
            ClassVisitor change = new ChangeVisitor(writer);
            // 4. 将 ClassVisitor 对象传入 ClassReader 中
            reader.accept(change, ClassReader.EXPAND_FRAMES);

            Class clazz = new MyClassLoader().defineClass(className, writer.toByteArray());
            Object personObj = clazz.newInstance();
            Method nameMethod = clazz.getDeclaredMethod("sayHello", null);
            nameMethod.invoke(personObj, null);
            System.out.println("Success!");
            // 获取修改后的 class 文件对应的字节数组
            byte[] code = writer.toByteArray();
            try {
                // 将二进制流写到本地磁盘上
                FileOutputStream fos = new FileOutputStream("/Users/sheyang/LearnJavaProject/src/main/java/asm/HelloWorld2.class");
                fos.write(code);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failure!");
        }
    }

    static class ChangeVisitor extends ClassVisitor {

        ChangeVisitor(ClassVisitor classVisitor) {
            super(Opcodes.ASM5, classVisitor);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
//            MethodVisitor methodVisitor = super.visitMethod(access, name, desc, signature, exceptions);
//            if (name.equals("<init>")) {
//                return methodVisitor;
//            }
//            return new ChangeAdapter(Opcodes.ASM4, methodVisitor, access, name, desc)
//打印出方法名和类型签名
            System.out.println(name + " " + desc);
            return super.visitMethod(access, name, desc, signature, exceptions);
        }
    }

//    static class ChangeAdapter extends MethodVisitor {
//        private int startTimeId = -1;
//
//        private String methodName = null;
//
//        ChangeAdapter(int api, MethodVisitor mv, int access, String name, String desc) {
//            super(api, mv, access, name, desc);
//            methodName = name;
//        }
//
//        @Override
//        protected void onMethodEnter() {
//            super.onMethodEnter();
//            startTimeId = newLocal(Type.LONG_TYPE);
//            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
//            mv.visitIntInsn(LSTORE, startTimeId);
//        }
//
//        @Override
//        protected void onMethodExit(int opcode) {
//            super.onMethodExit(opcode);
//            int durationId = newLocal(Type.LONG_TYPE);
//            mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
//            mv.visitVarInsn(LLOAD, startTimeId);
//            mv.visitInsn(LSUB);
//            mv.visitVarInsn(LSTORE, durationId);
//            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//            mv.visitTypeInsn(NEW, "java/lang/StringBuilder");
//            mv.visitInsn(DUP);
//            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
//            mv.visitLdcInsn("The cost time of " + methodName + " is ");
//            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
//            mv.visitVarInsn(LLOAD, durationId);
//            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(J)Ljava/lang/StringBuilder;", false);
//            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
//            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
//        }
//    }
}

class MyClassLoader extends ClassLoader {
    public MyClassLoader() {
        super();
    }

    public MyClassLoader(ClassLoader cl) {
        super(cl);
    }

    public Class<?> defineClass(String name, byte[] b) {
        return defineClass(name, b, 0, b.length);
    }
}