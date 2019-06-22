package TestThread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Logger;

/**
 * created at 2019/5/30
 *
 * @author sheyang
 */
public class TestCyclicBarrierThread {

    private static Logger LOGGER = Logger.getLogger("lavasoft");

    public static void main(String[] args){
        //构造器：设置屏障放开前做的事情
        CyclicBarrier barrier3 = new CyclicBarrier(2, () -> {
            LOGGER.info("屏障放开，[屏障线程]先运行！");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.info("[屏障线程]的事情做完了!");
        });
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                LOGGER.info(Thread.currentThread().getName() + " 等待屏障放开");
                try {
                    barrier3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                LOGGER.info(Thread.currentThread().getName() + "开始干活...干活结束");
            }).start();
        }
    }
    
}
