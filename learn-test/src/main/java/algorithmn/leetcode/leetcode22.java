package algorithmn.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * created at 2019/7/20
 * 回溯
 *
 * @author sheyang
 */
public class leetcode22 {

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, String cur, int open, int close, int max) {
        if (cur.length() == (max << 1)) {
            ans.add(cur);
            return;
        }

        if (open < max) {
            backtrack(ans, cur + "(", open + 1, close, max);
        }
        if (close < open) {
            backtrack(ans, cur + ")", open, close + 1, max);
        }
    }

    public static void main(String[] args) {
        leetcode22 leetcode22 = new leetcode22();
        System.out.println(leetcode22.generateParenthesis(3));
    }


}
