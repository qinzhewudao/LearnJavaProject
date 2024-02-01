package algorithmn.leetcode;

import java.util.Arrays;

/**
 * created at 2019/7/18
 *
 * @author sheyang
 */
public class leetcode115 {

//    public int numDistinct(String s, String t) {
//
//        int[][] dp = new int[t.length() + 1][s.length() + 1];
//
//        for (int j = 0; j < s.length() + 1; j++) {
//            dp[0][j] = 1;
//        }
//
//        for (int i = 1; i < t.length() + 1; i++) {
//            for (int j = 1; j < s.length() + 1; j++) {
//                if (t.charAt(i - 1) == s.charAt(j - 1)) {
//                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
//                } else {
//                    dp[i][j] = dp[i][j - 1];
//                }
//            }
//        }
//
//        return dp[t.length()][s.length()];
//    }

    public int numDistinct(String s, String t) {
        int n = t.length();
        int[] dists = new int[n + 1];
        dists[n] = 1;

        int[] firsts = new int[128];
        Arrays.fill(firsts, -1);

        int[] next = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            int c = t.charAt(i);
            next[i] = firsts[c];
            firsts[c] = i;
        }

        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            for (int j = firsts[c]; j >= 0; j = next[j]) {
                dists[j] += dists[j + 1];
            }
        }

        return dists[0];
    }

    public static void main(String[] args) {
        leetcode115 leetcode115 = new leetcode115();
        System.out.println(leetcode115.numDistinct("rabbbit", "rabbit"));
    }


}
