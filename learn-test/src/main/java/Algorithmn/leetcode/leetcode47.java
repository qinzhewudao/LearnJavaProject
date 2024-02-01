package Algorithmn.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * created at 2019/7/20
 *
 * @author sheyang
 */
public class leetcode47 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        //切记必须要先排序啊！！！！！！这样只有相邻的才可能相等，才可以判断去除！！！！！
        Arrays.sort(nums);
        boolean[] flag = new boolean[nums.length];
        backtrack(result, new ArrayList<>(), nums, flag, 0);
        return result;
    }

    public void backtrack(List<List<Integer>> result, List<Integer> current, int[] nums, boolean[] visited, int index) {
        if (index == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //这个位置用过了
            if (visited[i]) {
                continue;
            }
            //i-1和i的值相等，且i-1没被用过（之后可能会被用就产生重复）
            if (i > 0 && nums[i - 1] == nums[i] && !visited[i - 1]) {
                continue;
            }
            current.add(nums[i]);
            visited[i] = true;
            backtrack(result, current, nums, visited, index + 1);
            current.remove(current.size() - 1);
            visited[i] = false;
        }
    }

}
