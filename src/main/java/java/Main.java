package java;

import java.util.Scanner;

/**
 * created at 2019/5/18
 *
 * @author sheyang
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int D = scanner.nextInt();
        int[] location = new int[N];
        for (int i = 0; i < N; i++) {
            location[i] = scanner.nextInt();
        }

        long num = 0;

        for (int i = 2, j = 0; i < N; i++) {
            while (j < i && location[i] - location[j] > D) {
                j++;
            }
            long choose = i - j;
            if (choose >= 2) {
                num += choose * (choose - 1) / 2;
            }
        }
        int mods = 99997867;
        System.out.println(num % mods);
    }

}
