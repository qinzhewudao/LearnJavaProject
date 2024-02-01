package algorithmn.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * created at 2019/7/13
 *
 * @author sheyang
 */
public class leetcode1 {

    public static int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(numbers[i])) {
                return new int[]{map.get(numbers[i]) + 1, i + 1};
            } else {
                map.put(target - numbers[i], i);
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{3, 2, 4}, 6)));
    }


}
