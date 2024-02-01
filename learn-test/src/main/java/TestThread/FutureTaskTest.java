package TestThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Callable<String> task = () -> {
            System.out.println("Sleep start.");
            try {
                Thread.sleep(1000 * 5);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("Sleep end.");
            return "time=" + System.currentTimeMillis();
        };

        //直接使用Thread的方式执行
        FutureTask<String> ft = new FutureTask<>(task);
        Thread t = new Thread(ft);
        t.start();
        try {
            System.out.println("waiting execute result by Thread");
            System.out.println("result = " + ft.get());
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //使用Executors来执行
        System.out.println("=========");
        FutureTask<String> ft2 = new FutureTask<>(task);
        Executors.newSingleThreadExecutor().submit(ft2);
        try {
            System.out.println("waiting execute result by Executors");
            System.out.println("result = " + ft2.get());
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
