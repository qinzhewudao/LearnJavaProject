package TestThread;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * created at 2019/6/8
 *
 * @author sheyang
 */
public class TestVolatile {

    private volatile boolean flag = true;

    private final int finalInt;

    public TestVolatile(int finalInt) {
        this.finalInt = finalInt;
    }

    public static void main(String[] args) throws InterruptedException {

        TestVolatile testVolatile = new TestVolatile(5);

        Thread threadA = new Thread(() -> {
            while(testVolatile.flag){
                System.out.println("flag don't change");
            }
            System.out.println("flag already change");
        });

        Thread threadB = new Thread(() -> testVolatile.flag = false);

        threadA.start();

        threadB.start();

        threadA.join();

        threadB.join();

        ExecutorService executor = new ThreadPoolExecutor(10,20,
                0L,TimeUnit.SECONDS, new LinkedBlockingDeque<>(10),new CustomizableThreadFactory());


    }

}
