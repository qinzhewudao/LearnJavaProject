package TestList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author sheyang
 * @date 2021/6/28 3:49 下午
 */
public class TestRemove {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        //换成CopyOnWriteArrayList 不会有并发异常
        list.add(1);
        list.add(2);
        System.out.println(list);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            long time = System.currentTimeMillis();
            System.out.println(time);
            Integer integer = iterator.next();
            if (integer == 1) {
                list.remove(integer);
            }
            System.out.println(time);
        }
        System.out.println(list);
    }

}
