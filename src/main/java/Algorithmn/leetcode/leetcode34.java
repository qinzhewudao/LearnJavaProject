package Algorithmn.leetcode;

import java.util.HashMap;

/**
 * created at 2019/7/21
 *
 * @author sheyang
 */
public class leetcode34 {

    public int[] searchRange(int[] nums, int target) {
        if (null == nums || 0 >= nums.length) {
            return new int[]{-1, -1};
        }
        //找出最左的边界
        int left = index(nums, target, false);
        //找出最右的边界
        int right = index(nums, target, true);
        //根据输入的数据个数不同，查找出来的左右边界会出现不同的值
        //少一位或者多一位，但是少或者多一个位数
        //所以我们可以判断一下 下一个数是否等于target
        //对于左边的边界值left加1  右边的边界值减1
        //以上是对于存在的情况
        //如果不存在这个数则会返回left = right = 0;
        //这时我们只需要判断下个数是否等于target 如果不等于就返回-1
        if (nums[left] != target) {
            left++;
            left = left >= nums.length ? -1 : nums[left] != target ? -1 : left;
        }
        if (nums[right] != target) {
            right--;
            right = right < 0 ? -1 : nums[right] != target ? -1 : right;
        }
        return new int[]{left, right};
    }

    public int index(int[] nums, int target, boolean flag) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            //如果查找为相同的则左移  查找右边界
            //如果查找为相同的则右移  查找左边界
            if (target == nums[mid]) {
                if (flag) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        leetcode34 leetcode34 = new leetcode34();
        for (int num : leetcode34.searchRange(new int[]{5, 7, 7, 8, 8, 8, 10}, 8)) {
            System.out.println(num);
        }
    }

    public int searchInsert(int[] nums, int target) {
        if(null == nums || 0 >= nums.length){
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        while(left < right){
            int mid = left + ((right - left) >> 1);
            if(target > nums[mid]){
                left = mid + 1;
            } else if(target < nums[mid]){
                right = mid - 1;
            } else if(target == nums[mid]){
                return mid;
            }
        }
        return left;
    }

}
