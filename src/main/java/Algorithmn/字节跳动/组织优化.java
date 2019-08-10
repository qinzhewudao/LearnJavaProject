package Algorithmn.字节跳动;

import java.util.Scanner;

/**
 * @author sheyang
 * @description
 * @date 2019/8/10
 * @time 下午3:53
 */
public class 组织优化 {

    static void dfs(int[][] v, int i, int j, int n) {
        if (i < 0 || i >= n || j < 0 || j >= n || (v[i][j] == 0)) {
            return;
        }
        v[i][j] = 0;
        dfs(v, i - 1, j, n);
        dfs(v, i + 1, j, n);
        dfs(v, i, j - 1, n);
        dfs(v, i, j + 1, n);
    }

    public static void main(String[] args) {
        int n;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();

        int[][] v = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                v[i][j] = scanner.nextInt();
            }
        }

        int result = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (v[i][j] == 1) {
                    ++result;
                    dfs(v, i, j, n);
                }
            }
        }

        System.out.println(result);
    }

}
