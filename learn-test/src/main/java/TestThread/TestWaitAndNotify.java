package TestThread;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sheyang
 * @date 2021/7/5 2:40 下午
 */
public class TestWaitAndNotify {

    public static void main(String[] args) throws InterruptedException {
        BreadContainer container = new BreadContainerLock();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                new Thread(new Producer(container)).start();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                new Thread(new Consumer(container)).start();
            }
        }).start();

        TimeUnit.SECONDS.sleep(10);
    }

}


// 生产者
class Producer implements Runnable {
    private final BreadContainer container;

    public Producer(BreadContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        // 生产者生产面包
        container.put(new Bread());
    }
}

// 消费者
class Consumer implements Runnable {

    private final BreadContainer container;

    public Consumer(BreadContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        // 消费者消费面包
        container.take();
    }
}

interface BreadContainer {

    void put(Bread bread);

    void take();

}

class BreadContainerSynchronized implements BreadContainer {

    LinkedList<Bread> list = new LinkedList<>();
    // 容器容量
    private final static int CAPACITY = 10;

    /**
     * 放入面包
     */
    @Override
    public synchronized void put(Bread bread) {
        while (list.size() == CAPACITY) {
            try {
                // 如果容器已满，则阻塞生产者线程
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(bread);
        // 面包生产成功后通知消费者线程
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " product a bread" + bread.toString() + " size = " + list.size());
    }

    /**
     * 取出面包
     */
    @Override
    public synchronized void take() {
        while (list.isEmpty()) {
            try {
                // 如果容器为空，则阻塞消费者线程
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Bread bread = list.removeFirst();
        // 消费后通知生产者生产面包
        notifyAll();
        System.out.println("Consumer " + Thread.currentThread().getName() + " consume a bread" + bread.toString() + " size = " + list.size());
    }
}

class BreadContainerLock implements BreadContainer {
    LinkedList<Bread> list = new LinkedList<>();
    private final static int CAPACITY = 10;
    Lock lock = new ReentrantLock();
    private final Condition providerCondition = lock.newCondition();
    private final Condition consumerCondition = lock.newCondition();

    @Override
    public void put(Bread bread) {
        lock.lock();
        try {
            while (list.size() == CAPACITY) {
                try {
                    // 如果容器已满，则阻塞生产者线程
                    providerCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(bread);
            // 面包生产成功后通知消费者线程
            consumerCondition.signalAll();
            System.out.println(Thread.currentThread().getName() + " product a bread" + bread.toString() + " size = " + list.size());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void take() {
        lock.lock();
        try {
            while (list.isEmpty()) {
                try {
                    // 如果容器为空，则阻塞消费者线程
                    consumerCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Bread bread = list.removeFirst();
            // 消费后通知生产者生产面包
            providerCondition.signalAll();
            System.out.println("Consumer " + Thread.currentThread().getName() + " consume a bread" + bread.toString() + " size = " + list.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}


class Bread {

}