package algorithmn.leetcode;

/**
 * created at 2019/7/21
 *
 * @author sheyang
 */
public class leetcode44 {

    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] f = new boolean[m + 1][n + 1];

        f[0][0] = true;
        for (int i = 1; i <= n; i++) {
            f[0][i] = f[0][i - 1] && p.charAt(i - 1) == '*';
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    f[i][j] = f[i - 1][j - 1];
                }
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 1] || f[i - 1][j];
                }
            }
        }
        return f[m][n];
    }

    public static void main(String[] args){
        leetcode44 leetcode44 = new leetcode44();
        System.out.println(leetcode44.isMatch("acdcb","a*c?b"));
    }


}
