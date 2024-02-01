package TestCollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * author sheyang
 * created at 2018/8/5
 */
public class TestCollection1 {

    public static void main(String[] args) {
        List<Integer> integers1 = new ArrayList<Integer>();
        List<Integer> integers2 = new ArrayList<Integer>();
        integers1.add(1);
        integers1.add(2);
        integers1.add(3);

        integers2.add(2);
        integers2.add(3);
        integers2.add(4);

        integers1.removeAll(integers2);

        integers1.addAll(integers2);

        for (Integer integer : integers1) {
            System.out.println(integer);
        }

        Collection<Integer> integers = new ArrayList<>();

        System.out.println("#####################");

        List list = new ArrayList();
        //ArrayList:底层数组实现，查询快，插入删除慢
        //LinkedList：底层实现是链表，查询慢，但是插入删除比较方便
        //vector：线程安全的，其余基本和ArrayList一样,效率低


        list.add("aaa");
        list.add(new Date(System.currentTimeMillis()));
        list.add(1234);

        System.out.println(list.size());

        System.out.println(list.get(0));

        System.out.println(list.get(1));

    }

}
