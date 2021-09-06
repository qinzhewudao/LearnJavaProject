package TestMath;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author sheyang
 * @date 2021/7/14 2:48 下午
 */
public class TestRandom2 {

    public static void main(String[] args) {
        Random random = new Random();

        byte[] data = new byte[5];
        random.nextBytes(data);
        // [59, 19, -35, 46, 36]
        System.out.println(Arrays.toString(data));

        // true
        System.out.println(random.nextBoolean());
        // [0.0,1.0) double值 0.9081744035754137
        System.out.println(random.nextDouble());
        // [2.2,9.0) double值 6.2376803909751075
        System.out.println(random.nextDouble() * 6.8 + 2.2);
        // [0.0,1.0) float值 0.79999745
        System.out.println(random.nextFloat());
        System.out.println(random.nextGaussian());
        System.out.println(random.nextInt());
        System.out.println(random.nextInt(100));
        System.out.println(random.nextLong());

        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

        System.out.println();

        System.out.println(threadLocalRandom.nextDouble());
        System.out.println(threadLocalRandom.nextDouble() * 6.8 + 2.2);
        System.out.println(threadLocalRandom.nextDouble(2.2, 2.9));
        System.out.println(threadLocalRandom.nextInt());
        System.out.println(threadLocalRandom.nextInt(49, 99));

    }


}
