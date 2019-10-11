package Algorithmn.leetcode;

/**
 * @author sheyang
 * @description 荷兰三色旗问题解
 * @date 2019/9/15
 * @time 下午3:14
 */
public class leetcode75 {

    public void sortColors(int[] nums) {
        int p0right = 0;
        int p2left = nums.length - 1;
        int current = 0;
        while (current <= p2left) {
            if (0 == nums[current]) {
                int tmp = nums[p0right];
                nums[p0right++] = nums[current];
                nums[current++] = tmp;
            } else if (2 == nums[current]) {
                int tmp = nums[p2left];
                nums[p2left--] = nums[current];
                nums[current] = tmp;
            } else {
                current++;
            }
        }
    }

    public static void main(String[] args) {
        leetcode75 leetcode75 = new leetcode75();
        int[] nums = new int[]{2, 0, 2, 1, 1, 0};
        leetcode75.sortColors(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }

}
