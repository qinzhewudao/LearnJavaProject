package Algorithmn;

/**
 * created at 2019/7/6
 *
 * @author sheyang
 */
public class Sort {

    /**
     * 快排
     * 分治
     *
     * @param nums
     * @param low
     * @param high
     */
    public static void quickSort(int[] nums, int low, int high) {
        int i = low;
        int j = high;
        if (low < high) {
            //将L[low,high]一分为二,算出枢轴值pivot,该值得位置固定,不用再变化
            int pivot = nums[low];
            // 此处的while循环结束，则完成了元素key的位置调整
            //顺序很重要，要先从右边找
            while (i < j) {
                //从后往前找到比key小的放到前面去
                while (i < j && pivot <= nums[j]) {
                    j--;
                }
                nums[i] = nums[j];
                //从前往后找到比key大的 放到后面去
                while (i < j && pivot >= nums[i]) {
                    i++;
                }
                nums[j] = nums[i];

                nums[i] = pivot;
            }
            quickSort(nums, low, i - 1);
            quickSort(nums, i + 1, high);
        }
    }

    /**
     * 冒泡排序
     *
     * @param nums 数组
     */
    public static void bubbleSort(int[] nums) {
        if (null == nums || 1 >= nums.length) {
            return;
        }

        //设置标记位
        boolean flag;

        for (int i = nums.length - 1; i > 0; i--) {
            flag = false;
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    // 有交换时，将标志位重置为true
                    flag = true;

                }
            }

            // 若没发生交换，则说明数列已有序。
            if (!flag) {
                return;
            }
        }

    }

    /**
     * 直接插入排序算法
     * eg:扑克牌
     *
     * @param nums 数组
     */
    public static void insertSort(int[] nums) {
        if (null == nums || 1 >= nums.length) {
            return;
        }
        for (int i = 1; i < nums.length; i++) {
            //为a[i]在前面的a[0...i-1]有序区间中找一个合适的位置
            int temp = nums[i];
            int j;
            // 记得是j>=0不要忘记等号，否则，前两个将不参与比较
            for (j = i - 1; j >= 0 && nums[j] > temp; j--) {
                //将前面的较大元素往后移动
                nums[j + 1] = nums[j];
            }
            // 将num[i]放在指定位置
            nums[j + 1] = temp;
        }
    }

    /**
     * 希尔排序，分组的直接插入排序
     * 多加了一成循环，增量d的初始值为数组长度的一半
     *
     * @param nums 数组
     */
    public static void shellSort(int[] nums) {
        if (null == nums || 1 >= nums.length) {
            return;
        }
        for (int d = nums.length / 2; d > 0; d = d / 2) {
            for (int i = d; i < nums.length; i += d) {
                int temp = nums[i];
                int j;
                for (j = i - d; j >= 0 && nums[j] > temp; j -= d) {
                    nums[j + d] = nums[j];
                }
                nums[j + d] = temp;
            }
        }
    }

    /**
     * 折半插入排序: 插入的时候利用二分查找寻找该插入的位置
     * left = 0;
     * right = i-1; 目标是将第i个元素插入到前面的某个位置，所以right为i-1
     *
     * @param nums
     */
    public void halfinsertSort(int[] nums) {
        if (null == nums || 1 >= nums.length) {
            return;
        }
        for (int i = 1; i < nums.length; i++) {
            int left = 0;
            int right = i - 1;
            int temp = nums[i];
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] > temp) {
                    right = mid - 1;
                } else if (nums[mid] < temp) {
                    left = mid + 1;
                }
            }
            // 经过上边的循环，知道num[i]应该插入到left位置
            int j;
            for (j = i - 1; j >= left; j--) {
                //将前面的较大元素往后移动
                nums[j + 1] = nums[j];
            }
            // 将num[i]放在指定位置
            nums[left] = temp;
        }
    }

    /**
     * 选择排序：外层循环控制着趟数，每趟的num[i]我们认为是最小值
     *
     * @param nums
     */
    public void selectSort(int[] nums) {
        if (null == nums || 1 >= nums.length) {
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 内层循环j=i+1,外层循环控制着循环次数。
            // 即每趟中num[i]这个值就是本趟的最小值。i位置上是最小值
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }

    /**
     * 堆排序：
     * 1、构建大顶堆
     * 2、交换堆顶和末尾元素
     * 3、继续构建大顶堆
     *
     * @param nums
     */
    public static void heapSort(int[] nums) {
        if (null == nums || 1 >= nums.length) {
            return;
        }
        // 构建大顶堆
        for (int parent = (nums.length) / 2; parent >= 0; parent--) {
            getMaxHeap(nums, parent, nums.length - 1);
        }
        // 取堆顶元素进行交换，重新构建大顶堆
        for (int t = nums.length - 1; t > 0; t--) {
            swap(nums, 0, t);
            getMaxHeap(nums, 0, t - 1);
        }
    }


    /**
     * 交换数组中的两个位置的元素
     *
     * @param num
     * @param i
     * @param t
     */
    private static void swap(int[] num, int i, int t) {
        if (num == null) {
            return;
        }
        int temp = num[i];
        num[i] = num[t];
        num[t] = temp;
    }

    /**
     * 构建大顶堆的方法
     * s 代表拥有左右孩子节点的节点，即本次要调整位置的节点
     * length 代表当前堆的长度
     *
     * @param nums
     * @param s
     * @param length
     */
    private static void getMaxHeap(int[] nums, int s, int length) {
        if (null == nums || 1 >= nums.length) {
            return;
        }
        int temp = nums[s];
        // j=2*j+1是要找到j的孩子节点
        // j=2*s+1为s节点的左孩子，j+1为s节点的右孩子
        for (int j = 2 * s + 1; j < length; j = 2 * j + 1) {
            // 将j指向子节点中较大值
            if (nums[j] < nums[j + 1]) {
                j++;
            }
            // 判断父节点和子节点最大值的关系
            if (temp >= nums[j]) {
                break; // 如果s节点的值大于其最大的孩子节点值，则，循环结束，本次不做任何改变
            }
            // 否则
            // 否则将较大的孩子节点的值赋值给父节点
            nums[s] = nums[j];
            // 将j的值赋值给s，即j成为了下一波的父节点，继续比较
            s = j;
        }
        // 将temp放在指定位置
        nums[s] = temp;
    }


    public static int halfSearch(int[] nums, int left, int right, int target) {
        if (left <= right) {
            int mid = (left + right) / 2;
            int midValue = nums[mid];
            if (midValue > target) {
                return halfSearch(nums, left, mid - 1, target);
            }
            if (midValue < target) {
                return halfSearch(nums, mid + 1, right, target);
            }
            return mid;
        }
        return -1;
    }

    public static int halfSearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            int midValue = nums[mid];
            if (midValue > target) {
                right = mid - 1;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 6, 28, 3, 5, 10};

//        bubbleSort(nums);

//        quickSort(nums, 0, nums.length - 1);

//        insertSort(nums);

//        shellSort(nums);

        heapSort(nums);

        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println(halfSearch(nums, 0, nums.length - 1, 10));
        System.out.println(halfSearch(nums, 10));

        bubbleSort(nums);


    }

}
