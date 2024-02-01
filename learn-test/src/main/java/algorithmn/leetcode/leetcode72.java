package algorithmn.leetcode;

/**
 * created at 2019/7/27
 * <p>
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 示例 1:
 * <p>
 * 输入: word1 = "horse", word2 = "ros"
 * 输出: 3
 * 解释:
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2:
 * <p>
 * 输入: word1 = "intention", word2 = "execution"
 * 输出: 5
 * 解释:
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * <p>
 *
 * @author sheyang
 */
public class leetcode72 {

    public int minDistance(String word1, String word2) {

//        int n = word1.length();
//        int m = word2.length();
//
//        // if one of the strings is empty
//        if (n * m == 0) {
//            return n + m;
//        }
//
//        // array to store the convertion history
//        int[][] dp = new int[n + 1][m + 1];
//
//        // init boundaries
//        for (int i = 0; i < n + 1; i++) {
//            dp[i][0] = i;
//        }
//        for (int j = 0; j < m + 1; j++) {
//            dp[0][j] = j;
//        }
//
//        // DP compute
//        for (int i = 1; i < n + 1; i++) {
//            for (int j = 1; j < m + 1; j++) {
//                int left = dp[i - 1][j] + 1;
//                int down = dp[i][j - 1] + 1;
//                int leftDown = dp[i - 1][j - 1];
//                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
//                    leftDown += 1;
//                }
//                dp[i][j] = Math.min(left, Math.min(down, leftDown));
//
//            }
//        }
//        return dp[n][m];

        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();
        int[] dp = new int[w2.length + 1];
        for (int i = 1; i <= w2.length; i++) {
            dp[i] = i;
        }
        for (int i = 1; i <= w1.length; i++) {
            int pre = dp[0];
            dp[0] = i;
            for (int j = 1; j <= w2.length; j++) {
                int tmp = w1[i - 1] == w2[j - 1] ? pre : pre + 1;
                tmp = Math.min(tmp, dp[j] + 1);
                tmp = Math.min(tmp, dp[j - 1] + 1);
                pre = dp[j];
                dp[j] = tmp;
            }
        }
        return dp[dp.length - 1];
    }

}
