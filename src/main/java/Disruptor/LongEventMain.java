package Disruptor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * created at 2019/6/22
 *
 * @author sheyang
 */
public class LongEventMain {

    public static void handleEvent(LongEvent event, long sequence, boolean endOfBatch) {
        System.out.println("消费:" + event.getValue() + " ThreadName:" + Thread.currentThread().getName());
    }

    public static void translate(LongEvent event, long sequence, Long buffer) {
        event.setValue(buffer);
    }

    public static void main(String[] args) throws Exception {

        ThreadFactory consumer = new ThreadFactoryBuilder().setNameFormat("consumer-%d").build();

        ThreadFactory product = new ThreadFactoryBuilder().setNameFormat("product-%d").build();

        ThreadPoolExecutor productExecutor = new ThreadPoolExecutor(2, 2, 1, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), product);

        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 8;

        // Construct the Disruptor
        //Disruptor<LongEvent> disruptor = new Disruptor<>(factory, bufferSize, executor);
        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, bufferSize, consumer, ProducerType.SINGLE,
                new YieldingWaitStrategy());

        // Connect the handler disruptor 的消费者线程数是 根据消费者的handler的数量来计算的 如果只有一个handle就只会有一个消费线程
        disruptor.handleEventsWith(LongEventMain::handleEvent, LongEventMain::handleEvent);

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        for (long l = 0; l < 10000; l++) {
            long finalL = l;
            productExecutor.execute(() -> ringBuffer.publishEvent(LongEventMain::translate, finalL));
        }

        productExecutor.shutdown();
        while (!productExecutor.awaitTermination(1, TimeUnit.SECONDS)) {
            System.out.println("线程还在执行。。。");
        }
        disruptor.shutdown(1, TimeUnit.SECONDS);
        System.out.println("main over");
    }

}
