package TestArray;

import java.util.Arrays;

/**
 * @author sheyang
 * @date 2021/6/24 7:12 下午
 */
public class TestArray {

    public static int findDuplicate(int[] array) {
        for (int i = 1; i < array.length; i++) {
            array[i] ^= array[i - 1];
        }
        return array[array.length - 1];
    }

    public static int removeDuplicates(int[] array) {
        int index = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] != array[index]) {
                array[++index] = array[i];
            }
        }
        return ++index;
    }

    public static void main(String[] args) {
        int[] array = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(removeDuplicates(array));
        System.out.println(Arrays.toString(array));
        array = new int[]{1, 2};
        System.out.println(removeDuplicates(array));
        System.out.println(Arrays.toString(array));
        array = new int[]{1, 2, 2};
        System.out.println(removeDuplicates(array));
        System.out.println(Arrays.toString(array));
        System.out.println(findDuplicate(new int[]{1, 1, 2, 2, 3, 4, 4, 5, 5}));
    }

}
