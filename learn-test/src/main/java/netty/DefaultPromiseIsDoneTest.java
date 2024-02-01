package netty;


import io.netty.util.concurrent.GlobalEventExecutor;
import io.netty.util.concurrent.Promise;

/**
 * @author sheyang
 * @date 2021/7/1 7:47 下午
 */
public class DefaultPromiseIsDoneTest {

    private final Promise<?> defaultPromise = GlobalEventExecutor.INSTANCE.newPromise();

    public static void main(String[] args) {

        DefaultPromiseIsDoneTest main = new DefaultPromiseIsDoneTest();

        main.isDoneTest();

    }

    private void isDoneTest() {
        defaultPromise.setUncancellable();

        defaultPromise.cancel(false);

        /*
        首先来看java.util.concurrent.Future#cancel方法的javadoc约定：

        After this method returns, subsequent calls to isDone will always return true.
        就是说，在调用了cancel方法后，再调用isDone将永远返回true。

        Netty的Future接口继承了Java的Future接口:

        public interface Future<V> extends java.util.concurrent.Future<V>

        所以在实现的时候理应遵循这一约定，但Netty截止到目前的最新版本中(4.1.21)，并没有遵循这一约定
         */

        boolean isDone = defaultPromise.isDone();

        System.out.println(isDone);
    }

}
