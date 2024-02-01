package TestThread;

import java.util.concurrent.locks.LockSupport;

/**
 * @author sheyang
 * @date 2024/2/1 16:50
 */
public class TestPrintABC {

    static Thread t1, t2, t3;

    public static void main(String[] args) {
        t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                LockSupport.park();
                System.out.print("A");
                LockSupport.unpark(t2);
            }
        });
        t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                LockSupport.park();
                System.out.print("B");
                LockSupport.unpark(t3);
            }
        });
        t3 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                LockSupport.unpark(t1);
                LockSupport.park();
                System.out.println("C");
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }

}
