package Algorithmn.字节跳动;

/**
 * @author sheyang
 * @description
 * @date 2019/8/10
 * @time 上午11:29
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RedMan {

    private static ArrayList<Integer>[] directFans;
    private static Set<Integer>[] allFans;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //用户数
        int n = in.nextInt();
        //关系数
        int m = in.nextInt();
        int[][] conn = new int[m][2];
        directFans = new ArrayList[n + 1];
        //用户编号1开始，故创建N+1大小的数组
        allFans = new Set[n + 1];

        Set<Integer> persons = new HashSet<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 2; j++) {
                conn[i][j] = in.nextInt();
                persons.add(conn[i][j]);
            }
        }

        //为每个List/Collection分配内存
        for (int i = 0; i < n + 1; i++) {
            directFans[i] = new ArrayList<>();
            allFans[i] = new HashSet<>();
        }

        //将关系数组填充至graph
        for (int[] edge : conn) {
            directFans[edge[1]].add(edge[0]);
        }
        int count = 0;
        for (int person : persons) {
            if (countFans(person) == n) {
                count++;
            }
        }
        System.out.println(count);
    }

    /**
     * 统计用户粉丝数
     *
     * @param node
     * @return 粉丝个数
     */
    private static int countFans(int node) {
        //先放入自己
        allFans[node].add(node);
        //统计用户直接粉丝
        for (int fans : directFans[node]) {
            allFans[node].add(fans);
        }
        //统计用户间接粉丝
        for (int fans : directFans[node]) {
            for (int fansForFans : directFans[fans]) {
                //没放入ct的放进去并且count++；
                allFans[node].add(fansForFans);
            }
        }
        return allFans.length;
    }

}

