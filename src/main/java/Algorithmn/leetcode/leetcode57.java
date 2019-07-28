package Algorithmn.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * created at 2019/7/28
 *
 * @author sheyang
 */
public class leetcode57 {

    public int[][] insert(int[][] intervals, int[] newInterval) {

        if (null == intervals || null == newInterval) {
            return intervals;
        }

        int arrayLength = intervals.length;

        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < arrayLength; i++) {

            if (intervals[i][0] > newInterval[1]) {
                result.add(newInterval);
                for (int j = i; j < arrayLength; j++) {
                    result.add(intervals[j]);
                }
                return result.toArray(new int[0][]);
            }

            if (intervals[i][0] > newInterval[0] && intervals[i][0] <= newInterval[1] && intervals[i][1] >= newInterval[1]) {
                newInterval[1] = intervals[i][1];
                continue;
            }

            if (intervals[i][0] <= newInterval[0] && intervals[i][1] >= newInterval[1]) {
                newInterval[0] = intervals[i][0];
                newInterval[1] = intervals[i][1];
                continue;
            }

            if (intervals[i][0] < newInterval[0] && intervals[i][1] >= newInterval[0]) {
                newInterval[0] = intervals[i][0];
                continue;
            }

            if (intervals[i][1] < newInterval[0]) {
                result.add(intervals[i]);
            }

        }
        result.add(newInterval);
        return result.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        leetcode57 leetcode57 = new leetcode57();
        int[][] result = leetcode57.insert(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8});
        for (int[] ints : result) {
            for (int anInt : ints) {
                System.out.print(" " + anInt);
            }
            System.out.println();
        }
    }

}
