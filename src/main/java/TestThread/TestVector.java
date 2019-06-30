package TestThread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

/**
 * vector是线程安全的数组，但效率比ArrayList要慢
 * 效率不包括插入扩容，大量数据插入的时候，vector
 * 直接扩容一倍，ArrayList则是扩容百分之五十再加一
 * 所以vector可能更快，其余操作两者接近。一般都是ArrayList快
 * author sheyang
 * created at 2018/7/30
 */
public class TestVector {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Vector<Integer> vector = new Vector<>();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100_0000; i++) {
            list.add(i);
        }
        long end = System.currentTimeMillis();

        System.out.println("ArrayList插入1000000元素用时" + (end - start) + "ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < 100_0000; i++) {
            vector.add(i);
        }
        end = System.currentTimeMillis();

        System.out.println("Vector插入1000000元素用时" + (end - start) + "ms");


        ArrayList<Integer> arrayList2 = new ArrayList<>();
        arrayList2.add(2);
        arrayList2.add(3);
        arrayList2.add(4);
        arrayList2.add(5);
        arrayList2.add(1);

        arrayList2.removeIf(integer -> integer == 2);

        Thread thread1 = new Thread(() -> {
            Iterator<Integer> iterator = arrayList2.iterator();
            while (iterator.hasNext()) {
                Integer integer = iterator.next();
                System.out.println(integer);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            arrayList2.removeIf(integer -> integer == 2);
        });
        thread1.start();
        thread2.start();


    }

}
