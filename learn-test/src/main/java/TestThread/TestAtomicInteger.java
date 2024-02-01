package TestThread;

/**
 * created at 2019/8/4
 *
 * @author sheyang
 */
public class TestAtomicInteger {

    private static final int THREADS_COUNT = 20;

    public static int count = 0;

    private static void increase() {
        count++;
        System.out.println(Thread.currentThread().getName() + " count:"  + count);
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int i1 = 0; i1 < 1000; i1++) {
                    increase();
                }
            });
            threads[i].start();
        }

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }

        System.out.println(count);
    }

}
