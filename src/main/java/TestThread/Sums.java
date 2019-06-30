package TestThread;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.util.Arrays.asList;

/**
 * author sheyang
 * created at 2018/8/30
 */

public class Sums {

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<Long>> results = executor.invokeAll(asList(
                new Sum(0, 10), new Sum(0, 1_000), new Sum(0, 1_000_000)
        ));
        executor.shutdown();

        for (Future<Long> result : results) {
            System.out.println(result.get());
        }
    }

    static class Sum implements Callable<Long> {
        private final long from;
        private final long to;

        Sum(long from, long to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public Long call() {
            long acc = 0;
            for (long i = from; i <= to; i++) {
                acc = acc + i;
            }
            System.out.println(Thread.currentThread().getName() + " : " + acc);
            return acc;
        }
    }
}
