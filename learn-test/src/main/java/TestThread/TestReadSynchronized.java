package TestThread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * author sheyang
 * created at 2018/7/28
 */
public class TestReadSynchronized {

    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args) throws InterruptedException {
        final TestReadSynchronized test = new TestReadSynchronized();

        new Thread(() -> test.getBySynchronized(Thread.currentThread())).start();

        new Thread(() -> test.getBySynchronized(Thread.currentThread())).start();

        TimeUnit.SECONDS.sleep(1);//等候2秒时间

        new Thread(() -> test.getByReentrantReadWriteLock(Thread.currentThread())).start();

        new Thread(() -> test.getByReentrantReadWriteLock(Thread.currentThread())).start();

    }

    public synchronized void getBySynchronized(Thread thread) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start <= 1) {
            System.out.println(thread.getName() + "正在进行读操作");
        }
        System.out.println(thread.getName() + "读操作完毕");
    }

    public void getByReentrantReadWriteLock(Thread thread) {
        rwl.readLock().lock();
        try {
            long start = System.currentTimeMillis();

            while (System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName() + "正在进行读操作");
            }
            System.out.println(thread.getName() + "读操作完毕");
        } finally {
            rwl.readLock().unlock();
        }
    }
}

