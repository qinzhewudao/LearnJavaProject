package SwordOffer;

/**
 * created at 2019/6/30
 *
 * @author sheyang
 */
public class Singleton {

    /**
     * 饿汉
     */
    private static final Singleton SINGLETON_BY_HUNGER = new Singleton();
    /**
     * 懒汉
     */
    private static volatile Singleton singletonLazy = null;

    private Singleton() {
    }

    public Singleton getInstance() {
        if (null == singletonLazy) {
            synchronized (Singleton.class) {
                if (null == singletonLazy) {
                    return new Singleton();
                }
            }
        }
        return singletonLazy;
    }

}
