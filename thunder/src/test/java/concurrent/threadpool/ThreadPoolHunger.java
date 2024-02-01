package concurrent.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author sheyang
 * @date 2024/1/23 16:56
 */
@Slf4j
public class ThreadPoolHunger {

    private static final ExecutorService poolExecutor = new ThreadPoolExecutor(2, 2,
            0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(1),
            new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build(),
            new ThreadPoolExecutor.AbortPolicy() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                    log.error("rejectedExecution");
                    super.rejectedExecution(r, e);
                }
            }
    );

    /**
     * @author 认知科技技术团队
     * 微信公众号：认知科技技术团队
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<Object> future1 = poolExecutor.submit(() -> {
            Future<Object> future = poolExecutor.submit(() -> null);
            return future.get();
        });
        Future<Object> future2 = poolExecutor.submit(() -> {
            Future<Object> future = poolExecutor.submit(() -> null);
            return future.get();
        });

        future1.get();
        future2.get();

        poolExecutor.shutdown();
    }

}
