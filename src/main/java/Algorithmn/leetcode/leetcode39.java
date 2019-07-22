package Algorithmn.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * created at 2019/7/20
 *
 * @author sheyang
 */
public class leetcode39 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        //先排个序
        Arrays.sort(candidates);
        combinationSumHelp(res, list, candidates, target, 0);
        return res;
    }

    private boolean combinationSumHelp(List<List<Integer>> res, List<Integer> list, int[] candidates, int target,
                                       int start) {
        // 对减去后的target值进行判断,
        if (target < 0) {
            return false;
        } else if (target == 0) {
            // list对象一直处于变动中,不能直接导入list对象,
            List<Integer> temp = new ArrayList<>(list);
            res.add(temp);
            return false;
        } else {
            for (int i = start; i < candidates.length; i++) {
                list.add(candidates[i]);
                //深度优先遍历,就像一只深入地底的探测器
                boolean flag = combinationSumHelp(res, list, candidates, target - candidates[i], i);
                list.remove(list.size() - 1);
                //此路不通时,由于排序后的数组,i之后数字只能更大,跳出循环
                if (!flag) {
                    break;
                }
            }
        }
        return true;
    }

}
