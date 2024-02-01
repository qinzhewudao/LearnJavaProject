package cache;

import java.time.LocalDateTime;
import java.util.concurrent.locks.LockSupport;

/**
 * @author sheyang
 * @date 2021/5/20 5:05 下午
 */
public class LockSupportTest {

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now());
        LockSupport.unpark(Thread.currentThread());
        LockSupport.parkNanos(10L * 1000 * 1000 * 1000);
        System.out.println(LocalDateTime.now());
        Thread t3 = new Thread(() -> {
            Object waitObject = new Object();
            try {
//            先获取到waitObject的锁
                synchronized (waitObject) {
                    System.out.println(0);
                    waitObject.wait(3);
                    System.out.println(1);
                }
                System.out.println(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t3.start();
        while (Thread.activeCount() > 1) {
            System.out.println(t3.getState());
            return;
        }
    }

}
