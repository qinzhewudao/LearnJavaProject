package Algorithmn.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * created at 2019/7/20
 *
 * @author sheyang
 */
public class leetcode46 {

    public List<List<Integer>> permute(int[] nums) {
        // init output list
        List<List<Integer>> result = new LinkedList<>();

        // convert nums into list since the output is a list of lists
        ArrayList<Integer> current = new ArrayList<>();
        for (int num : nums) {
            current.add(num);
        }

        int n = nums.length;
        backtrack(result, current, n, 0);
        return result;
    }

    public void backtrack(List<List<Integer>> result, ArrayList<Integer> nums, int n,
                          int index) {
        // if all integers are used up
        if (index == n) {
            result.add(new ArrayList<>(nums));
        }

        for (int i = index; i < n; i++) {
            // place i-th integer first
            // in the current permutation
            Collections.swap(nums, index, i);
            // use next integers to complete the permutations
            backtrack(result, nums, n, index + 1);
            // backtrack
            Collections.swap(nums, index, i);
        }
    }

}
