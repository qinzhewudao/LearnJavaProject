package sort;

/**
 * @author sheyang
 * @date 2024/1/26 15:42
 */
public class ShellSortTest {


    public static void main(String[] args) {
        int[] array = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1};
        System.out.println("排序之前：");
        for (int value : array) {
            System.out.print(value + " ");
        }
        //希尔排序
        int gap = array.length;
        while (true) {
            gap /= 2;   //增量每次减半
            for (int i = 0; i < gap; i++) {
                for (int j = i + gap; j < array.length; j += gap) {//这个循环里其实就是一个插入排序
                    int k = j - gap;
                    while (k >= 0 && array[k] > array[k + gap]) {
                        int temp = array[k];
                        array[k] = array[k + gap];
                        array[k + gap] = temp;
                        k -= gap;
                    }
                }
            }
            if (gap == 1)
                break;
        }

        System.out.println();
        System.out.println("排序之后：");
        for (int j : array) {
            System.out.print(j + " ");
        }
    }

}
