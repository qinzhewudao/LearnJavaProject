package Algorithmn.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * created at 2019/7/13
 *
 * @author sheyang
 */
public class leetcode15 {
    public List<List<Integer>> threeSum(int[] nums) {
        if (null == nums) {
            return new ArrayList<>();
        }
        int length = nums.length;
        if (length < 3) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < length; i++) {
            if (nums[i] > 0) {
                break;
            }
            // 去重
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            int left = i + 1;
            int right = length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (0 == sum) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 去重
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // 去重
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (0 > sum) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }
}
