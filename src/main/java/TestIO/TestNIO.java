package TestIO;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;

/**
 * @author sheyang
 * @description
 * @date 2019/9/6
 * @time 上午10:52
 */
public class TestNIO {

    public static void ioExample(String path) {
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(path));
            byte[] buf = new byte[1024];
            int bytesRead = in.read(buf);
            while (bytesRead != -1) {
                for (int i = 0; i < bytesRead; i++) {
                    System.out.print((char) buf[i]);
                }
                bytesRead = in.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void nioExample(String path) {
        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile(path, "rw");
            FileChannel fileChannel = aFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            int bytesRead = fileChannel.read(buf);
            System.out.println(bytesRead);
            while (bytesRead != -1) {
                buf.flip();
                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }
                buf.compact();
                bytesRead = fileChannel.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (aFile != null) {
                    aFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        String path = "/Users/sheyang/IdeaProjects/LearnJavaProject/src/main/resources/nio.txt";

        System.out.println("传统IO输出:");
        ioExample(path);

        System.out.println("\nNIO输出:");
        nioExample(path);

    }

}
