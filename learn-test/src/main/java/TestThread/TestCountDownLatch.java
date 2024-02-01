package TestThread;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.*;

/**
 * created at 2019/6/29
 *
 * @author sheyang
 */
public class TestCountDownLatch {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(10);
        ExecutorService service = new ThreadPoolExecutor(10, 10, 0L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(),
                new CustomizableThreadFactory("test_count_down_latch"));

        for (int i = 0; i < 10; i++) {
            service.execute(() -> {
                System.out.println("我是" + Thread.currentThread().getName());
                try {
                    TimeUnit.MICROSECONDS.sleep(20L);
                } catch (InterruptedException e) {
                    System.out.println("我是" + Thread.currentThread().getName() + "我被打断了");
                }
                for (int j = 0; j < 15; j++) {
                    countDownLatch.countDown();
                    System.out.println("计时器现在" + countDownLatch.getCount());
                }
                System.out.println("我是" + Thread.currentThread().getName() + "我完了");

            });
        }

        try {
            countDownLatch.await();
            service.shutdownNow();
        } catch (Exception ignored) {
        }

        System.out.println("主线程结束了");
    }

}
