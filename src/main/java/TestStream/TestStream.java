package TestStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * created at 2019/6/6
 *
 * @author sheyang
 */
public class TestStream {

    public static void main(String[] args) throws InterruptedException {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        numbers.parallelStream()
                .forEach(System.out::println);

        System.out.println("------------");

        numbers.parallelStream()
                .forEachOrdered(System.out::println);

        System.out.println("Hello World!");
        // 构造一个10000个元素的集合
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }
        // 统计并行执行list的线程
        Set<Thread> threadSet = new CopyOnWriteArraySet<>();
        // 并行执行
        list.parallelStream().forEach(integer -> {
            Thread thread = Thread.currentThread();
//             System.out.println(thread);
//             统计并行执行list的线程
            threadSet.add(thread);
        });
        System.out.println("threadSet一共有" + threadSet.size() + "个线程");
        System.out.println("系统共有"+Runtime.getRuntime().availableProcessors()+"个cpu");
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list1.add(i);
            list2.add(i);
        }
        Set<Thread> threadSetTwo = new CopyOnWriteArraySet<>();
        CountDownLatch countDownLatch = new CountDownLatch(2);

        //countDownLatch count不会减到负数
        countDownLatch.countDown();
        countDownLatch.countDown();
        countDownLatch.countDown();

        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            System.out.println("SingThreadExecutor start");
            list1.parallelStream().forEach(integer -> {
                Thread thread = Thread.currentThread();
                threadSetTwo.add(thread);
            });
            countDownLatch.countDown();
        });

        Thread threadA = new Thread(() -> {
            list1.parallelStream().forEach(integer -> {
                Thread thread = Thread.currentThread();
                threadSetTwo.add(thread);
            });
            countDownLatch.countDown();
        });

        Thread threadB = new Thread(() -> {
            list2.parallelStream().forEach(integer -> {
                Thread thread = Thread.currentThread();
                threadSetTwo.add(thread);
            });
            countDownLatch.countDown();
        });

        threadA.start();
        threadB.start();
        countDownLatch.await();
        System.out.println("threadSetTwo一共有" + threadSetTwo.size() + "个线程");

        System.out.println("---------------------------");
        System.out.println(threadSet);
        System.out.println(threadSetTwo);
        System.out.println("---------------------------");
        threadSetTwo.addAll(threadSet);
        System.out.println(threadSetTwo);
        System.out.println("threadSetTwo一共有" + threadSetTwo.size() + "个线程");
        System.out.println("系统一个有"+Runtime.getRuntime().availableProcessors()+"个cpu");

    }

}
