package TestList;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author sheyang
 * @date 2021/7/2 11:32 上午
 */
public class ConcurrentAddTest {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new CopyOnWriteArrayList<>();
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("test-list-").build();
        CountDownLatch countDownLatch = new CountDownLatch(100);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(100, 100, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1000), threadFactory);
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPoolExecutor.submit(() -> {
                b.add(finalI);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        CountDownLatch countDownLatch2 = new CountDownLatch(100);
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPoolExecutor.execute(() -> {
                a.add(finalI);
                countDownLatch2.countDown();
            });
        }
        countDownLatch2.await();
        System.out.println("a size:" + a.size() + a);
        System.out.println("b size:" + b.size() + b);
    }

}
