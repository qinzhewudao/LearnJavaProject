package algorithmn.leetcode;

/**
 * created at 2019/7/14
 * <p>
 * 思路一：分别计算每一层能接的雨水数，再逐层累加得到结果（在LeetCode中提交会超时）
 * 首先遍历数组得到最高的柱子的高度maxHeight，我们总共需要计算maxHeight层。
 * <p>
 * 计算第i层时，我们需要将柱子的高度减去i，这里i从0开始计数。我们寻找每一层的左边界left和右边界right，
 * 在[left, right]之间高度小于等于0处，我们可以令能接的雨水数加1。
 * <p>
 * 这个思路的时间复杂度很明显，是O(maxHeight * n)级别的，其中n为height数组的长度。而对于空间复杂度，
 * 我们使用了一个新的数组newHeight记录各柱子高度减去i时的值，因此是O(n)级别的。
 *
 * @author sheyang
 */
public class leetcode42 {

//    public int trap(int[] height) {
//        int n = height.length;
//        int result = 0;
//        if (n == 0) {
//            return result;
//        }
//        int maxHeight = height[0];
//        for (int i = 1; i < n; i++) {
//            if (height[i] > maxHeight) {
//                maxHeight = height[i];
//            }
//        }
//        int[] newHeight = new int[n];
//        for (int i = 0; i < maxHeight; i++) {
//            for (int j = 0; j < n; j++) {
//                newHeight[j] = height[j] - i;
//            }
//            int left = 0;
//            int right = n - 1;
//            while (left < n && newHeight[left] <= 0) {
//                left++;
//            }
//            while (right >= 0 && newHeight[right] <= 0) {
//                right--;
//            }
//            for (int j = left; j <= right; j++) {
//                if (newHeight[j] <= 0) {
//                    result++;
//                }
//            }
//        }
//        return result;
//    }

    public int trap(int[] height) {
        int n = height.length;
        int result = 0;
        if (n == 0 || n == 1) {
            return result;
        }
        int left = 0;
        int right = n - 1;
        int leftHeight = 0;
        int rightHeight = 0;
        while (left < right) {
            if (height[left] <= height[right]) {
                leftHeight = Math.max(leftHeight, height[left]);
                result += leftHeight - height[left];
                left++;
            } else {
                rightHeight = Math.max(rightHeight, height[right]);
                result += rightHeight - height[right];
                right--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        leetcode42 leetcode42 = new leetcode42();
        int[] a = new int[]{};
        System.out.println(leetcode42.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

}
