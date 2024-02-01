package TestThread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * created at 2019/6/29
 *
 * @author sheyang
 */
public class TestCompletableFuture {

    public static void main(String[] args) throws InterruptedException {

        CompletableFuture.supplyAsync(() -> 1)
                .thenApply(i -> i + 1)
                .thenApply(i -> i * i)
                .whenComplete((r, e) -> System.out.println(r));

        CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(s -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return s + " World";
                })
                .thenApply(String::toUpperCase)
                .whenComplete((r, e) -> System.out.println(r));

        System.out.println("主线程");
    }

}
