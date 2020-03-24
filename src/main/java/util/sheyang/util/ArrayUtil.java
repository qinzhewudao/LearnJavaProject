package util.sheyang.util;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: sheyang
 * @date: 2020/3/23 5:13 下午
 */
public class ArrayUtil {

    public static Long[] rightShift(Long[] origin) {
        int size = origin.length;
        Long[] result = new Long[size];
        if (size - 1 >= 0) {
            System.arraycopy(origin, 1, result, 0, size - 1);
        }
        result[size - 1] = origin[0];
        return result;
    }

    public static void main(String[] args) {
        Long[] a = new Long[]{1L, 2L, 3L, 4L, 5L};
        System.out.println(Arrays.toString(rightShift(a)));

        for (int i = 0; i < 10; i++) {
            int index = new Random().nextInt(6 - 3) + 3;
            System.out.println(index);
        }
    }

}
