package Algorithmn.字节跳动;

import java.util.Scanner;

/**
 * @author sheyang
 * @description
 * @date 2019/8/10
 * @time 上午10:36
 */
public class BreakThrough {

    /**
     * 构建一个图，dfs即可 。 本题也可以使用并查集   与LeetCode 朋友圈 这题一样
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] matrix = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            int num;
            matrix[i][i] = 1;
            while ((num = sc.nextInt()) != 0) {
                matrix[i][num] = 1;
                matrix[num][i] = 1;
            }
        }

        int cnt = 0;
        boolean[] visit = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (!visit[i]) {
                cnt++;
                dfs(matrix, visit, i);
            }
        }
        System.out.println(cnt);
    }

    private static void dfs(int[][] matrix, boolean[] visit, int i) {
        visit[i] = true;
        for (int j = 1; j < matrix.length; j++) {
            if (matrix[i][j] != 0 && !visit[j]) {
                dfs(matrix, visit, j);
            }
        }
        String s = "sda";
        for (char c : s.toCharArray()) {
            
        }
    }

}
