package TestThread;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

/**
 * created at 2019/5/30
 *
 * @author sheyang
 */
public class TestCyclicBarrierExample {

    private static Logger LOGGER = Logger.getLogger("lavasoft");

    public static void main(String[] args) {
        //数组大小
        int size = 50000000;
        //定义数组
        int[] numbers = new int[size];
        //随机初始化数组
        for (int i = 0; i < size; i++) {
            numbers[i] = new Random().nextInt(900) + 100;
        }

        Long start = System.currentTimeMillis();
        //单线程计算结果
        System.out.println();
        Long sum = 0L;
        for (int i = 0; i < size; i++) {
            sum += numbers[i];
        }
        AtomicReference<Long> end = new AtomicReference<>(System.currentTimeMillis());
        LOGGER.info("单线程计算结果：" + sum + "耗时：" + (end.get() - start));

        //多线程计算结果
        //定义线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //定义五个Future去保存子数组计算结果
        final int[] results = new int[5];

        start = System.currentTimeMillis();

        //定义一个循环屏障，在屏障线程中进行计算结果合并
        Long finalStart = start;
        CyclicBarrier barrier = new CyclicBarrier(5, () -> {
            int sums = 0;
            for (int i = 0; i < 5; i++) {
                sums += results[i];
            }
            end.set(System.currentTimeMillis());
            LOGGER.info("多线程计算结果：" + sums + "耗时：" + (end.get() - finalStart));
        });

        //子数组长度
        int length = 10000;
        //定义五个线程去计算
        for (int i = 0; i < 5; i++) {
            //定义子数组
            int[] subNumbers = Arrays.copyOfRange(numbers, (i * length), ((i + 1) * length));
            //盛放计算结果
            int finalI = i;
            executorService.submit(() -> {
                for (int subNumber : subNumbers) {
                    results[finalI] += subNumber;
                }
                //等待其他线程进行计算
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }

        //关闭线程池
        executorService.shutdown();
    }

}
