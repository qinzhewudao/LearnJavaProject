package algorithmn.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sheyang
 * @description 给定一个长度为n的数组，寻找其中最大的k个数
 * @date 2019/10/14
 * @time 下午8:01
 */
public class FindKthElements {

    /**
     * 算法一：排序，时间复杂度O(nlogn)，空间复杂度O(1)
     *
     * @param arr
     * @param k
     * @return
     */
    public ArrayList<Integer> findKthElements(int[] arr, int k) {

        ArrayList<Integer> res = new ArrayList<>();
        if (arr == null || arr.length <= 0 || arr.length < k) {
            return res;
        }

        Arrays.sort(arr);
        for (int i = arr.length - 1; i > arr.length - 1 - k; i--) {
            res.add(arr[i]);
        }
        return res;
    }

    /**
     * 算法二；前面k个数都比后面的数的最大值要大，则前面k个数就是最大的k个，时间复杂度O(k*(n-k))，空间复杂度O(1)
     *
     * @param arr
     * @param k
     * @return
     */
    public ArrayList<Integer> findKthElements2(int[] arr, int k) {

        ArrayList<Integer> res = new ArrayList<>();
        if (arr == null || arr.length <= 0 || arr.length < k) {
            return res;
        }

        for (int i = 0; i < k; i++) {
            int maxValueIndex = this.getMaxValueIndex(arr, k);
            if (arr[maxValueIndex] > arr[i]) {
                int temp = arr[maxValueIndex];
                arr[maxValueIndex] = arr[i];
                arr[i] = temp;
            }
        }

        for (int i = 0; i < k; i++) {
            res.add(arr[i]);
        }
        return res;
    }

    /**
     * 选择排序：选出最大值的下标
     */
    public int getMaxValueIndex(int[] arr, int k) {

        int maxValueIndex = k;
        for (int i = k + 1; i < arr.length; i++) {
            if (arr[i] > arr[maxValueIndex]) {
                maxValueIndex = i;
            }
        }
        return maxValueIndex;
    }

    /**
     * 算法三：构建大顶堆，然后调整k次，得到最大的k个数。时间复杂度(k+1)O(nlogn)，空间复杂度O(1)
     */
    public ArrayList<Integer> findKthElements3(int[] arr, int k) {

        ArrayList<Integer> res = new ArrayList<>();
        if (arr == null || arr.length <= 0 || arr.length < k) {
            return res;
        }
        //构建大顶堆
        int len = arr.length;
        for (int i = len / 2; i < len; i++) {
            heapSort(arr, i, len);
        }
        //调整k次大顶堆
        for (int i = arr.length - 1; i > arr.length - 1 - k; i--) {
            //交换最大的值到底部
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            res.add(arr[i]);
            heapSort(arr, 0, i);
        }
        return res;
    }

    public void heapSort(int[] arr, int start, int len) {

        int parent = start;
        int leftChild = parent * 2 + 1;
        int parentValue = arr[parent];
        while (leftChild < len) {
            int rightChild = leftChild + 1;
            //在左右孩子里选一个较大的出来
            if (rightChild < len && arr[leftChild] < arr[rightChild]) {
                leftChild = rightChild;
            }
            if (parentValue > arr[leftChild]) {
                break;
            }
            arr[parent] = arr[leftChild];
            parent = leftChild;
            leftChild = parent * 2 + 1;
        }
        arr[parent] = parentValue;
    }

    public static void main(String[] args) {

        int[] arr = {9, 4, 5, 8, 2};
        FindKthElements fke = new FindKthElements();
        List<Integer> res = fke.findKthElements3(arr, 3);
        System.out.println(res);
    }
}
