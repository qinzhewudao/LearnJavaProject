package Algorithmn.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * created at 2019/7/20
 *
 * @author sheyang
 */
public class leetcode77 {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new LinkedList<>();
        LinkedList<Integer> current = new LinkedList<>();
        backtrack(result, current, n, k, 0);
        return result;
    }

//    public void backtrack(List<List<Integer>> result, LinkedList<Integer> current, int n, int k, int index) {
//        if (0 == k) {
//            result.add(new LinkedList<>(current));
//            return;
//        }
//        for (int i = index; i < n; i++) {
//            current.add(i + 1);
//            backtrack(result, current, n, k - 1, i + 1);
//            current.removeLast();
//        }
//    }

    public void backtrack(List<List<Integer>> res, LinkedList<Integer> current, int n, int k, int index) {
        if (current.size() == k) {
            res.add(new LinkedList<>(current));
            return;
        }
        //剪枝
        for (int i = (current.isEmpty() ? 1 : current.getLast() + 1); i <= n - k + index + 1; i++) {
            current.add(i);
            backtrack(res, current, n, k, index + 1);
            current.removeLast();
        }
    }

    public static void main(String[] args) {
        leetcode77 leetcode77 = new leetcode77();
        System.out.println(leetcode77.combine(4, 2));
    }

}
