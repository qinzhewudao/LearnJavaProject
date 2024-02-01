package asm;

import org.objectweb.asm.*;

import java.io.FileOutputStream;
import java.util.List;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;

/**
 * @author sheyang
 * @date 2021/7/1 2:33 下午
 */
public class Test {

    public static void main(String[] args) throws Exception {
        ClassPrinter printer = new ClassPrinter();
        //读取静态内部类Bazhang
        ClassReader cr = new ClassReader("asm.Test$SheYang");
        cr.accept(printer, 0);

        ClassReader cr2 = new ClassReader(SheYang.class.getName());
        ClassWriter cw = new ClassWriter(cr2, ClassWriter.COMPUTE_MAXS);

        cr2.accept(cw, Opcodes.ASM5);

        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "newFunc", "(Ljava/lang/String;)V", null, null);

        mv.visitInsn(Opcodes.RETURN);
        mv.visitEnd();

        // 获取生成的class文件对应的二进制流
        byte[] code = cw.toByteArray();

        //将二进制流写到out/下
        FileOutputStream fos = new FileOutputStream("target/SheYang222.class");
        fos.write(code);
        fos.close();
    }

    //静态内部类
    static class SheYang {

        public SheYang(int a) {
        }

        private long f(int n, String s, int[] arr) {
            return 0;
        }

        private void hi(double a, List<String> b) {

        }

        public void newFunc(String str) {

        }
    }

    static class ClassPrinter extends ClassVisitor {

        public ClassPrinter() {
            super(Opcodes.ASM5);
        }

        @Override
        public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
            super.visit(version, access, name, signature, superName, interfaces);
            //打印出父类name和本类name
            System.out.println(superName + " " + name);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            //打印出方法名和类型签名
            System.out.println(name + " " + desc);
            return super.visitMethod(access, name, desc, signature, exceptions);
        }
    }

}
