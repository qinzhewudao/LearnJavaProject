package TestThread;

/**
 * author sheyang
 * created at 2018/7/28
 * 测试Thread的sleep方法
 */
public class TestThread2 {
    private int i = 10;
    private Object object = new Object();

    public static void main(String[] args) {
        TestThread2 testThread2 = new TestThread2();
        MyThread thread1 = testThread2.new MyThread();
        MyThread thread2 = testThread2.new MyThread();
        thread1.start();
        thread2.start();
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            //sleep等待方法不会释放锁
            synchronized (object) {
                i++;
                System.out.println("i: " + i);
                try {
                    System.out.println("线程 " + Thread.currentThread().getName() + " is sleeping");
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {

                }
                System.out.println("线程 " + Thread.currentThread().getName() + " sleep over");
                i++;
                System.out.println("i: " + i);
            }
        }
    }

}
