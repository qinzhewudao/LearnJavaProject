package Algorithmn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * created at 2019/7/6
 *
 * @author sheyang
 */


public class RedMan {

    private static ArrayList<Integer>[] directFans;
    private static Set<Integer>[] allFans;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        int[][] conn = new int[M][2];
        directFans = new ArrayList[N + 1];
        //用户编号1开始，故创建N+1大小的数组
        allFans = new Set[N + 1];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < 2; j++) {
                conn[i][j] = in.nextInt();
            }
        }
        for (int i = 0; i < N + 1; i++) {
            directFans[i] = new ArrayList<>();
            allFans[i] = new HashSet<>();
        }
        //为每个List/Collection分配内存
        //将关系数组填充至graph
        for (int[] edge : conn) {
            directFans[edge[1]].add(edge[0]);
        }
        int count = 0;
        for (int[] relation : conn) {
            if (countFans(relation[1]) == N) {
                count++;
            }
        }
        System.out.println(count);
    }

    /**
     * 统计用户粉丝数
     */
    private static int countFans(int relation) {
        allFans[relation].add(relation);
        int count = 1;
        //统计用户直接粉丝
        for (int fans : directFans[relation]) {
            if (!allFans[relation].contains(fans)) {
                allFans[relation].add(fans);
                count++;
            }
        }
        //统计用户间接粉丝
        for (int fans : directFans[relation]) {
            for (int realFans : directFans[fans]) {
                //没放入ct的放进去并且count++；
                if (!allFans[relation].contains(realFans)) {
                    allFans[relation].add(realFans);
                    count++;
                }
            }
        }
        return count;
    }

}