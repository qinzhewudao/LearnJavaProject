package algorithmn.leetcode;

/**
 * created at 2019/7/21
 *
 * @author sheyang
 */
public class leetcode33 {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) >> 1;
            long num = (nums[mid] < nums[0]) == (target < nums[0])
                    ? nums[mid]
                    : target < nums[0] ? Long.MIN_VALUE : Long.MAX_VALUE;
            if (num < target) {
                left = mid + 1;
            } else if (num > target) {
                right = mid;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        leetcode33 leetcode33 = new leetcode33();
        System.out.println(leetcode33.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
    }

}
