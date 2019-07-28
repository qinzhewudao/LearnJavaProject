package TestThread;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * created at 2019/7/28
 *
 * @author sheyang
 */
public class CountDownLatchExample {

    private static final int CORE_POOL_SIZE = 10;

    private static final int MAX_POOL_SIZE = 10;

    /**
     * 任务队列大小 防止并发 应大于一次任务可上传的数量 200
     */
    private static final int DEFAULT_CAPACITY = 1000;

    /**
     * 保留max pool size 的时间
     */
    private static final long KEEP_ALIVE_TIME = 60L;

    private static final TimeUnit TIME_UNIT_IN_SECONDS = TimeUnit.SECONDS;

    /**
     * 并发查询任务圈中人数 线程池
     */
    private static ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TIME_UNIT_IN_SECONDS,
            new LinkedBlockingQueue<>(DEFAULT_CAPACITY), new CustomizableThreadFactory("MarketActivityQueryAction"));

    private void example() {

        Map<Integer, List<Object>> activityMap = new ConcurrentHashMap<>();

        CountDownLatch countDownLatch = new CountDownLatch(16);


        List<Long> bindShops = new ArrayList<>();

        for (int i = 0, lastId = 0; lastId < bindShops.size(); i++) {
            lastId = Math.min((i + 1) * 100, bindShops.size());
            List<Long> subShopIds = bindShops.subList(i * 100, lastId);
            Integer finalI = i;

            EXECUTOR.execute(() -> {
                try {
                    //获取每一批店铺下冲突的活动信息
                    activityMap.put(finalI, new ArrayList<>(subShopIds));
                } catch (Throwable e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (List<Object> objects : activityMap.values()) {
            System.out.println(objects);
        }
    }

}
