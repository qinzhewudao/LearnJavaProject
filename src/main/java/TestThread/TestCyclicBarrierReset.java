package TestThread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * created at 2019/5/30
 *
 * @author sheyang
 */
public class TestCyclicBarrierReset {

    private static Logger LOGGER = Logger.getLogger("lavasoft");

    public static void main(String[] args) {
        CyclicBarrier barrier2 = new CyclicBarrier(2);
        //如果是一个初始的CyclicBarrier，则reset()之后，什么也不会发生
        LOGGER.info("如果是一个初始的CyclicBarrier，则reset()之后，什么也不会发生");
        barrier2.reset();
        System.out.println();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //如果是一个已经打开一次的CyclicBarrier，则reset()之后，什么也不会发生
        ExecutorService executorService2 = Executors.newCachedThreadPool();
        //等待两次
        for (int i = 0; i < 2; i++) {
            executorService2.submit(() -> {
                try {
                    LOGGER.info(Thread.currentThread().getName() + "开始执行.");
                    barrier2.await();
                    LOGGER.info(Thread.currentThread().getName() + "屏障已经打开.");
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                    LOGGER.info(Thread.currentThread().getName() + "被中断");
                } catch (BrokenBarrierException e) {
                    //e.printStackTrace();
                    LOGGER.info(Thread.currentThread().getName() + "被重置");
                }
            });
        }
        barrier2.reset();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        //如果是一个 有线程正在等待的线程，则reset()方法会使正在等待的线程抛出异常
        executorService2.submit(() -> {
            executorService2.submit(() -> {
                try {
                    barrier2.await();
                    LOGGER.info("333屏障已经打开.");
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                    LOGGER.info("333被中断");
                } catch (BrokenBarrierException e) {
                    LOGGER.info("在等待过程中，执行reset()方法，等待的线程抛出BrokenBarrierException异常，并不再等待");
                    //e.printStackTrace();
                }
            });
        });
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        barrier2.reset();
        executorService2.shutdown();
    }

}
