package Lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.async.RedisClusterAsyncCommands;

import java.util.concurrent.ExecutionException;

/**
 * @author sheyang
 * @date 2021/7/8 7:47 下午
 */
public class LettuceTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        RedisURI uri = RedisURI.builder()
                .withHost("r-bp1fr1olhpc5jbj0qkpi.redis.rds.aliyuncs.com")
                .withPassword("fy9u6dMSTbFNrKqn".toCharArray())
                .withPort(6379)
                .withDatabase(1)
                .build();
        RedisClusterClient client = RedisClusterClient.create(uri);
        StatefulRedisClusterConnection<String, String> connect = client.connect();
        RedisClusterAsyncCommands<String, String> async = connect.async();


        System.out.println(connect instanceof StatefulRedisConnection);



        RedisURI uri2 = RedisURI.builder()
                .withHost("r-bp1fr1olhpc5jbj0qk.redis.rds.aliyuncs.com")
                .withPassword("fy9u6dMSTbFNrKqn".toCharArray())
                .withPort(6379)
                .withDatabase(4)
                .build();
        RedisClient client2 = RedisClient.create(uri2);
        StatefulRedisConnection<String, String> connect2 = client2.connect();
        RedisClusterAsyncCommands<String, String> async2 = connect2.async();

        System.out.println(connect2 instanceof StatefulRedisClusterConnection);


        // 断点1
        System.out.println(async.get("beacon_ssd").get());
        RedisFuture<String> a = async.set("beacon_ssd", "v1");
        RedisFuture<String> a1 = async.get("beacon_ssd");
        System.out.println(a1.get());

        RedisFuture<Long> score = async.zadd("beacon_score", 1, "1");
        RedisFuture<Double> score1 = async.zscore("beacon_score", "1");
        System.out.println(score1.get());

        // 断点2
        System.out.println(async.get("beacon_ssd").get());
        RedisFuture<String> b = async.set("beacon_ssd", "v2");
        RedisFuture<String> b1 = async.get("beacon_ssd");
        System.out.println(b1.get());

        // 断点1
        RedisFuture<String> c = async2.set("key1", "v1");
        // 断点2
        RedisFuture<String> d = async2.set("key2", "v2");

        connect.close();
        client.shutdown();
    }

}
