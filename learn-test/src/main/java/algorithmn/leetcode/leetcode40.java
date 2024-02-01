package algorithmn.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * created at 2019/7/20
 *
 * @author sheyang
 */
public class leetcode40 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        //排序
        Arrays.sort(candidates);
        backtrack(result, list, candidates, target, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> list, int[] candidates, int target,
                           int index) {
        if (target == 0) {
            //需add新的数组
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = index;i < candidates.length; i++) {
            //此路不通时,由于排序后的数组,i之后数字只能更大,跳出循环
            if (candidates[i] > target) {
                return;
            }
            // 这一步之所以能够生效，其前提是数组一定是排好序的，这样才能保证：
            // 在递归调用的统一深度（层）中，一个元素只使用一次。
            // 这一步剪枝操作基于 candidates 数组是排序数组的前提下
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            //深度优先遍历,就像一只深入地底的探测器
            list.add(candidates[i]);
            // 【关键】因为元素不可以重复使用，这里递归传递下去的是 index + 1 而不是 i
            backtrack(result, list, candidates, target - candidates[i], i + 1);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args){
        leetcode40 leetcode40 = new leetcode40();
        System.out.println(leetcode40.combinationSum(new int[]{10,1,2,7,6,1,5},8));
    }

}
