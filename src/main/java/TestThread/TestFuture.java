package TestThread;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * created at 2019/6/29
 *
 * @author sheyang
 */
public class TestFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = new ThreadPoolExecutor(3, 3,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(), new CustomizableThreadFactory("test_future"));

        Long startTime = System.currentTimeMillis();

        List<Future<Integer>> futureList = new ArrayList<>();

        futureList.add(executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception ignored) {

            }
            System.out.println(Thread.currentThread().getName() + "我花费了" + (System.currentTimeMillis() - startTime) / 1000);
            return 1;
        }));

        futureList.add(executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception ignored) {

            }
            System.out.println(Thread.currentThread().getName() + "我花费了" + (System.currentTimeMillis() - startTime) / 1000);
            return 2;
        }));

        for (Future<Integer> future : futureList) {
            System.out.println(future.get());
        }

        //注意用到了future才会阻塞
        System.out.println("主线程花费了" + (System.currentTimeMillis() - startTime) / 1000);


        CompletableFuture<Integer> completableFuture1 = (CompletableFuture<Integer>) executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception ignored) {

            }
            System.out.println(Thread.currentThread().getName() + "我花费了" + (System.currentTimeMillis() - startTime) / 1000);
            return 1;
        });

        CompletableFuture<Integer> completableFuture2 = (CompletableFuture<Integer>) executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception ignored) {

            }
            System.out.println(Thread.currentThread().getName() + "我花费了" + (System.currentTimeMillis() - startTime) / 1000);
            return 2;
        });


    }
}
