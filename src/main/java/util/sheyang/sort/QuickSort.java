package util.sheyang.sort;

import java.util.Arrays;
import java.util.List;

/**
 * created at 2019/6/13
 *
 * @author sheyang
 */
public class QuickSort {

    /*******************************************************
     *快速排序 (比较排序类)
     *每次排序将待排记录分割两部分,一部分都比关键字小,一部分都比关键字大
     ********/
    public static void quickSort(List<Integer> list) {
        if (null == list || list.isEmpty() || 1 == list.size()) {
            return;
        }
        quickSort(list, 0, list.size() - 1);
    }

    public static void quickSort(List<Integer> list, int low, int high) {
        int pivot;

        if (low < high) {
            //将L[low,high]一分为二,算出枢轴值pivot,该值得位置固定,不用再变化
            pivot = partition(list, low, high);

            //对两边的数组分别排序
            quickSort(list, low, pivot - 1);
            quickSort(list, pivot + 1, high);
        }

    }

    /**
     * 选择一个枢轴值(关键字) 把它放到某个位置 使其左边的值都比它小 右边的值都比它大
     */
    private static int partition(List<Integer> list, int low, int high) {
        int pivot = low;
        Integer pivotKey = list.get(pivot);

        //顺序很重要，要先从右边找
        while (low < high) {

            //从后往前找到比key小的放到前面去
            if (list.get(high) >= pivotKey) {
                high--;
            }
            swap(list, low, high);

            //从前往后找到比key大的 放到后面去
            if (low < high && list.get(low) <= pivotKey) {
                low++;
            }
            swap(list, low, high);

        }
        //遍历所有记录  low的位置即为 key所在位置, 且固定,不用再改变
        return low;

    }

    private static void swap(List<Integer> list, int low, int high) {
        int temp = list.get(low);
        list.set(low, list.get(high));
        list.set(high, temp);
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(10, 2, 7, 4, 5);

        System.out.println(list);
        quickSort(list);
        System.out.println(list);

    }


}
