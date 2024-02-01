package TestStream;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @author sheyang
 * @date 2021/6/21 3:04 下午
 */
public class TestParallelStream {

    public static void main(String[] args) {
        //使用一个容器装载100个数字，通过Stream并行处理的方式将容器中为单数的数字转移到容器parallelList
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            integerList.add(i);
        }
        List<Integer> parallelList = new CopyOnWriteArrayList<>();
        integerList.parallelStream().filter(i -> i % 2 == 1).forEach(parallelList::add);
        System.out.println(parallelList);

        System.out.println(integerList.parallelStream().filter(i -> i % 2 == 1).collect(Collectors.toList()));
    }

}
