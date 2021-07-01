package Redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

/**
 * @author sheyang
 * @date 2021/6/29 2:55 下午
 */
public class TestLock {

    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://r-bp1a89fc54c31384331.redis.rds.aliyuncs.com:6379").setDatabase(88).setPassword("HT4s6S46cOfJzD9b");
        RedissonClient redissonClient = Redisson.create(config);
        RLock rLock = redissonClient.getLock("sheyang");
        rLock.lock();
        TimeUnit.SECONDS.sleep(30);
        rLock.unlock();
    }

}
