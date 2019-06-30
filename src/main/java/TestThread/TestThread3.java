package TestThread;

import java.io.IOException;

/**
 * author sheyang
 * created at 2018/7/28
 * 测试线程的join方法
 */
public class TestThread3 {
    public static void main(String[] args) throws IOException {
        System.out.println("进入线程" + Thread.currentThread().getName());
        TestThread3 test = new TestThread3();
        MyThread thread1 = test.new MyThread();
        thread1.start();
        try {
            System.out.println("线程" + Thread.currentThread().getName() + "等待");
            thread1.join();
            System.out.println("线程" + Thread.currentThread().getName() + "继续执行");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("进入线程" + Thread.currentThread().getName());
            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                // TODO: handle exception
            }
            System.out.println("线程" + Thread.currentThread().getName() + "执行完毕");
        }
    }
}
