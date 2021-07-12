package TestThread;

import java.util.concurrent.CompletableFuture;

/**
 * @author sheyang
 * @date 2021/7/1 5:02 下午
 */
public class JDKThreadPoolExecutorTest {

    public static void main(String[] args) throws Exception {
        CompletableFuture<?> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "-女神：我开始化妆了，好了我叫你。");
            throw new RuntimeException("男神约我看电影了，我们下次再约吧，你是个好人。");
        }).handleAsync((result, exception) -> {
            if (exception != null) {
                System.out.println(Thread.currentThread().getName() + "-女神放你鸽子了！");
                return exception.getCause();
            } else {
                return result;
            }
        }).thenApplyAsync((returnStr) -> {
            System.out.println(Thread.currentThread().getName() + "-" + returnStr);
            return returnStr;
        });
        System.out.println(Thread.currentThread().getName() + "-等女神化妆的时候可以干点自己的事情。");
        System.out.println("get的结果:" + future.get());
    }

}
