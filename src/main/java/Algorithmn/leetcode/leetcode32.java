package Algorithmn.leetcode;

/**
 * created at 2019/7/21
 *
 * @author sheyang
 */
public class leetcode32 {

    public int longestValidParentheses(String s) {
        int n = s.length();
        if (1 >= n) {
            return 0;
        }
        int[] dp = new int[n + 1];
        int max = 0;
        for (int i = 2; i <= n; i++) {
            //当前位置为右括号再开始计算
            if (s.charAt(i - 1) == ')') {
                //与当前位置前一位组成成对的括号
                if (s.charAt(i - 2) == '(') {
                    dp[i] = dp[i - 2] + 2;
                }
                //前一位也为右括号且去除前一位的成对括号数之后是左括号
                //即(())去除中间的括号位置 第一个左括号位置 i -2  - dp[i - 1]
                else if (s.charAt(i - 2) == ')' && i - 2 - dp[i - 1] >= 0 && s.charAt(i - 2 - dp[i - 1]) == '(') {
                    dp[i] = dp[i - 1] + dp[i - 2 - dp[i - 1]] + 2;
                }
                max = Math.max(dp[i], max);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        leetcode32 leetcode32 = new leetcode32();
        System.out.println(leetcode32.longestValidParentheses(")(()()))"));
    }


}
