package algorithmn.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * created at 2019/7/28
 *
 * @author sheyang
 */
public class leetcode60 {

    public String getPermutation(int n, int k) {
        List<Integer> num = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            num.add(i);
        }
        int[] factorial = new int[n];
        factorial[0] = 1;
        for (int i = 1; i < n; i++) {
            factorial[i] = i * factorial[i - 1];
        }
        n--;
        StringBuilder res = new StringBuilder();
        while (n >= 0) {
            int t = factorial[n];
            int loc = (int) (Math.ceil((double) k / (double) t)) - 1;
            if (loc == -1) {
                loc = num.size() - 1;
            }
            res.append(num.get(loc));
            num.remove(loc);
            k %= t;
            n--;
        }
        return res.toString();
    }

    public String getPermutation2(int n, int k) {
        int[] fact = new int[n];
        fact[0] = 1;
        for (int i = 1; i < n; i++) {
            fact[i] = fact[i - 1] * (i + 1);
        }
        int[] res = new int[n];
        boolean[] used = new boolean[n];
        int cur = 0;

        for (int i = n; i >= 2; i--) {
            if (k == 1 || k == 0) {
                break;
            }

            int nowK = fact[i - 2];
            int count = k % nowK == 0 ? k / nowK : k / nowK + 1;

            k = k - nowK * (count - 1);
            for (int j = 0; j < n; j++) {
                if (!used[j]) {
                    count--;
                }
                if (count == 0) {
                    res[cur] = j + 1;
                    cur++;
                    used[j] = true;
                    break;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                res[cur] = i + 1;
                used[i] = true;
                cur++;
            }
        }

        StringBuilder tempStr = new StringBuilder();
        for (int i = 0; i < n; i++) {
            tempStr.append(res[i]);
        }
        return tempStr.toString();
    }

    public static void main(String[] args) {
//        leetcode60 leetcode60 = new leetcode60();
//        System.out.println(leetcode60.getPermutation(3, 3));

        //牛顿法
        long result = 9;
        while (result * result > 9) {
            result = (result + 9 / result) / 2;
        }

    }


}
