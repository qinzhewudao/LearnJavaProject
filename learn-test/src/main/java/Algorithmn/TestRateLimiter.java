package Algorithmn;

import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * created at 2019/6/23
 *
 * @author sheyang
 */
public class TestRateLimiter {

    private final static Logger LOGGER = LoggerFactory.getLogger(TestRateLimiter.class);

    public static void main(String[] args) {
        //首先通过RateLimiter.create(1);创建一个限流器，参数代表每秒生成的令牌数
        RateLimiter limiter = RateLimiter.create(1);
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //批量调用
        Long startTime = System.currentTimeMillis();
        for (int i = 1; i < 5; i++) {
            //通过limiter.acquire(i);来以阻塞的方式获取令牌
            double acquire = limiter.acquire(i);
            LOGGER.info("获取令牌成功!,消耗=" + acquire);
        }
        Long endTime = System.currentTimeMillis();
        LOGGER.info("获取4个令牌耗时:" + (endTime - startTime) / 1000);

    }

}
