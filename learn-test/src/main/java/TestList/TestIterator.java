package TestList;

import java.util.ArrayList;
import java.util.List;

/**
 * created at 2019/6/30
 *
 * @author sheyang
 */
public class TestIterator {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        list.removeIf(integer -> integer == 1);

        System.out.println(list);

    }

}
