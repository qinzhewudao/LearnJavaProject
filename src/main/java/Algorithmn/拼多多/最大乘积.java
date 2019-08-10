package Algorithmn.拼多多;

import java.util.Scanner;

/**
 * @author sheyang
 * @description 题目描述
 * <p>
 * 给定一个无序数组，包含正数、负数和0，要求从中找出3个数的乘积，使得乘积最大，要求时间复杂度：O(n)，空间复杂度：O(1)
 * <p>
 * 输入描述:
 * 无序整数数组A[n]
 * 1
 * 输出描述:
 * <p>
 * 满足条件的最大乘积
 * 1
 * 输入
 * <p>
 * 3 4 1 2
 * 1
 * 输出
 * <p>
 * 24
 * 1
 * 定义五个数，一个最大，一个次大，一个第三大，一个最小，一个次小。
 * 只要找到这五个数，问题就解决了。因为最大乘积只可能是
 * 最大* 次大*第三大  或者是 最大*最小*次小。时间复杂度O(n),空间复杂度O(1)。
 * 一定要注意存储的数组类型是long型（之前是int型就没通过）
 * @date 2019/8/10
 * @time 上午11:15
 */
public class 最大乘积 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextLong();
        }
        getLargestMaul(arr, n);
    }

    private static void getLargestMaul(long[] arr, int len) {
        long max1 = 0, max2 = 0, max3 = 0, min1 = 0, min2 = 0;
        for (int i = 0; i < len; i++) {
            if (arr[i] != 0) {
                if (arr[i] > max1) {
                    max3 = max2;
                    max2 = max1;
                    max1 = arr[i];
                } else if (arr[i] > max2) {
                    max3 = max2;
                    max2 = arr[i];
                } else if (arr[i] > max3) {
                    max3 = arr[i];
                } else if (arr[i] < min1) {
                    min2 = min1;
                    min1 = arr[i];
                } else if (arr[i] > min1 && arr[i] < min2) {
                    min2 = arr[i];
                }
            }
        }
        long max = Math.max(max1 * max2 * max3, max1 * min1 * min2);
        System.out.println(max);
    }

}
