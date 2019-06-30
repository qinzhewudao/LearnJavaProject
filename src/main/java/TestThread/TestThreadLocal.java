package TestThread;

/**
 * ThreadLocal常用来保证数据库connection和session在每个线程之中都有自己独立的备份而不用占用太多资源
 * author sheyang
 * created at 2018/7/30
 */
public class TestThreadLocal {

    private ThreadLocal<Long> longLocal = ThreadLocal.withInitial(() -> Thread.currentThread().getId());

    /**
     * 重写了initialValue方法，这样即使不用set也能使元素有值
     */
    private ThreadLocal<String> stringLocal = ThreadLocal.withInitial(() -> Thread.currentThread().getName());

    public static void main(String[] args) throws InterruptedException {

        final TestThreadLocal test = new TestThreadLocal();

        //test.set();//如果不先set就get会出现空指针错误

        System.out.println(test);

        System.out.println("##################");
        Thread thread0 = new Thread(() -> System.out.println(test));
        thread0.start();
        thread0.join();

        System.out.println("##################");

        System.out.println(test);

    }

    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public long getLong() {
        return longLocal.get();

    }

    public String getString() {
        return stringLocal.get();

    }

    @Override
    public String toString() {
        return getLong() + " " + getString();
    }

}
