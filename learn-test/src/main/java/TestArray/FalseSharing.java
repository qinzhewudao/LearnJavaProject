package TestArray;

/**
 * 在2G Hz，2核，8G内存, jdk 1.7.0_45 的运行环境下，使用了共享机制比没有使用共享机制，速度快了4倍左右。
 * <p>
 * 结果：
 * <p>
 * Thread num 1 duration = 447
 * Thread num 2 duration = 463
 * Thread num 3 duration = 454
 * Thread num 4 duration = 464
 * Thread num 5 duration = 561
 * Thread num 6 duration = 606
 * Thread num 7 duration = 684
 * Thread num 8 duration = 870
 * Thread num 9 duration = 823
 * 把代码中ValuePadding都替换为ValueNoPadding后的结果：
 * <p>
 * Thread num 1 duration = 446
 * Thread num 2 duration = 2549
 * Thread num 3 duration = 2898
 * Thread num 4 duration = 3931
 * Thread num 5 duration = 4716
 * Thread num 6 duration = 5424
 * Thread num 7 duration = 4868
 * Thread num 8 duration = 4595
 * Thread num 9 duration = 4540
 * 备注：在jdk1.8中，有专门的注解@Contended来避免伪共享，更优雅地解决问题。
 *
 * @author sheyang
 * @date 2021/6/30 2:55 下午
 */

public class FalseSharing implements Runnable {
    public final static long ITERATIONS = 500L * 1000L * 100L;
    private int arrayIndex = 0;

    private static ValuePadding[] longs;

    public FalseSharing(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(final String[] args) throws Exception {
        for (int i = 1; i < 10; i++) {
            System.gc();
            final long start = System.currentTimeMillis();
            runTest(i);
            System.out.println("Thread num " + i + " duration = " + (System.currentTimeMillis() - start));
        }

    }

    private static void runTest(int NUM_THREADS) throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        longs = new ValuePadding[NUM_THREADS];
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new ValuePadding();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharing(i));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }

    @Override
    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = 0L;
        }
    }

    public final static class ValuePadding {
        protected long p1, p2, p3, p4, p5, p6, p7;
        protected volatile long value = 0L;
        protected long p9, p10, p11, p12, p13, p14;
        protected long p15;
    }

    public final static class ValueNoPadding {
        // protected long p1, p2, p3, p4, p5, p6, p7;
        protected volatile long value = 0L;
        // protected long p9, p10, p11, p12, p13, p14, p15;
    }
}

