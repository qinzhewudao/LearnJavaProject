package TestCache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author sheyang
 * @date 2021/7/8 10:33 上午
 */
public class CaffeineTest {

    private static final Cache<String, Integer> DEPENDENT_IP_STATUS_CACHE = Caffeine.newBuilder()
            .maximumSize(10000)
            .expireAfter(new Expiry<String, Integer>() {

                @Override
                public long expireAfterCreate(@NonNull String key, @NonNull Integer value, long currentTime) {
                    return 2000_000_000L;
                }

                @Override
                public long expireAfterUpdate(@NonNull String key, @NonNull Integer value, long currentTime, @NonNegative long currentDuration) {
                    return currentDuration;
                }

                @Override
                public long expireAfterRead(@NonNull String key, @NonNull Integer value, long currentTime, @NonNegative long currentDuration) {
                    return currentDuration;
                }
            })
            .build();

    public static void main(String[] args) throws InterruptedException {
        String sheyang = "sheyang";
        DEPENDENT_IP_STATUS_CACHE.put(sheyang, 1);
        System.out.println(DEPENDENT_IP_STATUS_CACHE.getIfPresent(sheyang));
        TimeUnit.SECONDS.sleep(1);
        DEPENDENT_IP_STATUS_CACHE.put(sheyang, 1);
        System.out.println(DEPENDENT_IP_STATUS_CACHE.getIfPresent(sheyang));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(DEPENDENT_IP_STATUS_CACHE.getIfPresent(sheyang));
        TimeUnit.SECONDS.sleep(1);
        DEPENDENT_IP_STATUS_CACHE.put(sheyang, 1);
        System.out.println(DEPENDENT_IP_STATUS_CACHE.getIfPresent(sheyang));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(DEPENDENT_IP_STATUS_CACHE.getIfPresent(sheyang));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(DEPENDENT_IP_STATUS_CACHE.getIfPresent(sheyang));
        String[] ipArray = DEPENDENT_IP_STATUS_CACHE.asMap().keySet().toArray(new String[0]);
        System.out.println(Arrays.toString(ipArray));
    }

}
