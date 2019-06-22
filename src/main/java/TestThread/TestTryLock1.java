package TestThread;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author sheyang
 * created at 2018/7/28
 */
public class TestTryLock1 {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    private Lock lock = new ReentrantLock();    //注意这个地方
    public static void main(String[] args)  {
        final TestTryLock1 test = new TestTryLock1();

        new Thread(() -> test.insert(Thread.currentThread())).start();

        new Thread(() -> test.insert(Thread.currentThread())).start();
    }

    public void insert(Thread thread) {
        if(lock.tryLock()) {
            try {
                System.out.println(thread.getName()+"得到了锁");
                for(int i=0;i<4;i++) {
                    arrayList.add(i);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }finally {
                System.out.println(thread.getName()+"释放了锁");
                lock.unlock();
            }
        } else {
            System.out.println(thread.getName()+"获取锁失败");
        }
    }
}

