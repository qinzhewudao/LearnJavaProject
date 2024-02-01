package algorithmn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * created at 2019/6/23
 * Function:{1,3,5,7} target=8 返回{2,3}
 *
 * @author sheyang
 */
public class TwoSumEqualTarget {

    /**
     * 时间复杂度 O(N)
     * 利用Map Key存放目标值和当前值的差值，value 就是当前的下标
     * 每次遍历是 查看当前遍历的值是否等于差值，如果是等于，说明两次相加就等于目标值。
     * 然后取出 map 中 value ，和本次遍历的下标，就是两个下标值相加等于目标值了。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSumEqualTarget(int[] nums, int target) {

        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                result = new int[]{map.get(nums[i]), i};
            } else {
                map.put(target - nums[i], i);
            }
        }
        return result;
    }

    public static void main(String[] args) {

        int[] nums = new int[]{1, 3, 5, 6, 3};
        System.out.println(Arrays.toString(twoSumEqualTarget(nums, 11)));

    }


}
