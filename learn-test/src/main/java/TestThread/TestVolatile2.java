package TestThread;

import java.util.concurrent.TimeUnit;

/**
 * @author sheyang
 * @date 2021/6/25 4:59 下午
 */
public class TestVolatile2 {

    private static boolean flag = false;

    private static int i = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
                flag = true;
                System.out.println("flag 被修改成 true");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        while (!flag) {
           i = 10;
        }
        System.out.println("程序结束,i=" + i);
    }

}
