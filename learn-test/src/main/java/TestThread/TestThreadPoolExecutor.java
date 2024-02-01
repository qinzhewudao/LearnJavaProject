package TestThread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * author sheyang
 * created at 2018/7/30
 */
public class TestThreadPoolExecutor {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("test-").build();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5), threadFactory);

//        for (int i = 0; i < 15; i++) {
//            MyTask myTask = new MyTask(i);
//            executor.execute(myTask);
//            System.out.println("线程池中的线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
//                    executor.getQueue().size() + ",已执行完的任务数目：" + executor.getCompletedTaskCount());
//        }


        Future<?> future = executor.submit(() -> System.out.println("这次一定！"));
        System.out.println(future.get());

        AtomicInteger atomicInteger = new AtomicInteger();
        future = executor.submit(() -> atomicInteger.set(5201314), atomicInteger);
        System.out.println(future.get());

        executor.shutdown();

    }

}


class MyTask implements Runnable {
    private int taskNum;

    MyTask(int num) {
        this.taskNum = num;
    }

    @Override
    public void run() {
        System.out.println("正在执行task" + taskNum);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task" + taskNum + "执行完毕");
    }
}