package Algorithmn.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * created at 2019/7/28
 * <p>
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 * <p>
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * <p>
 *
 * @author sheyang
 */
public class leetcode56 {

    public int[][] merge(int[][] intervals) {

        int n;
        if (intervals == null || 1 >= (n = intervals.length)) {
            return intervals;
        }

        //sort后方便对比
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> res = new ArrayList<>();
        int i = 0;
        while (i < n) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            while (i < n - 1 && intervals[i + 1][0] <= right) {
                i++;
                right = Math.max(right, intervals[i][1]);
            }
            res.add(new int[]{left, right});
            i++;
        }

        //指定返回数组size为0 会自动扩容
        return res.toArray(new int[0][]);

    }

    public int[][] merge2(int[][] intervals) {

        int arrayLength;
        if (null == intervals || (arrayLength = intervals.length) < 2) {
            return intervals;
        }

        //标记合并过的元素
        boolean[] merged = new boolean[arrayLength];
        // 合并过的计数，用于最后初始化结果数组长度
        int mergedCount = 0;

        for (int i = 0; i < arrayLength; i++) {
            int[] a = intervals[i];

            for (int j = i + 1; j < arrayLength; j++) {
                int[] b = intervals[j];

                // 区间重叠，合并到后面一个元素上，前一个元素标记为已合并
                if (a[1] >= b[0] && a[0] <= b[1]) {
                    merged[i] = true;
                    mergedCount++;

                    b[0] = Math.min(a[0], b[0]);
                    b[1] = Math.max(a[1], b[1]);
                    break;
                }
            }
        }

        // 组装返回结果
        int[][] result = new int[arrayLength - mergedCount][];
        for (int i = 0, pos = 0; i < arrayLength; i++) {
            if (!merged[i]) {
                result[pos++] = intervals[i];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        leetcode56 leetcode56 = new leetcode56();
        leetcode56.merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
    }


}
