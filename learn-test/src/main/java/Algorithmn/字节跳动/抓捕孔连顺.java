package Algorithmn.字节跳动;

/**
 * @author sheyang
 * @description
 * @date 2019/8/10
 * @time 下午4:18
 */

import java.util.Scanner;

public class 抓捕孔连顺 {

    private static int allWay(int[] building, int n, int d) {
        long count = 0;
        if (n < 3) {
            return 0;
        }
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                if (building[j] - building[i] > d) {
                    break;
                }
                for (int v = j + 1; v < n; v++) {
                    if (building[v] - building[i] > d) {
                        break;
                    }
                    count++;
//                    System.out.println("当前方案" + building[i] + "," + building[j] + "," + building[v]);
                }
            }
        }
        return (int) (count % 99997867);
    }

    private static int func(int[] pos, int n, int d) {
        /*dp[i]表示以pos[i]为最大位置的方案数*/
        int[] dp = new int[n];
        dp[2] = (pos[2] - pos[0] <= d) ? 1 : 0;
        int result = dp[2];

        int j = 0;
        for (int i = 3; i < n; ++i) {
            while (j < i && pos[j] + d < pos[i]) {
                ++j;
            }
            int diff = i - j;
            dp[i] = diff < 3 ? 0 : diff * (diff - 1) / 2;
            dp[i] %= 99997867;

            result += dp[i];
            result %= 99997867;
        }
        return result;

    }


    public static void main(String[] args) {
        int n, d;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        d = scanner.nextInt();
        int[] building = new int[n];
        for (int i = 0; i < n; i++) {
            building[i] = scanner.nextInt();
        }
        System.out.println(func(building, n, d));
    }

}
