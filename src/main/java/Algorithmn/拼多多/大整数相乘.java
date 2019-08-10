package Algorithmn.拼多多;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author sheyang
 * @description 题目描述
 * <p>
 * 有两个用字符串表示的非常大的大整数,算出他们的乘积，也是用字符串表示。不能用系统自带的大整数类型。
 * <p>
 * 输入描述:
 * <p>
 * 空格分隔的两个字符串，代表输入的两个大整数
 * 1
 * 输出描述:
 * <p>
 * 输入的乘积，用字符串表示
 * 1
 * 示例1
 * <p>
 * 输入
 * <p>
 * 72106547548473106236 982161082972751393
 * 1
 * 输出
 * <p>
 * 70820244829634538040848656466105986748
 * @date 2019/8/10
 * @time 上午11:18
 */
public class 大整数相乘 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        char[] c1 = line.split(" ")[0].toCharArray();
        char[] c2 = line.split(" ")[1].toCharArray();
        int[] a = new int[c1.length];
        int[] b = new int[c2.length];
        int[] result = new int[a.length + b.length];
        // 调整顺序，顺便全部赋值为整型数
        for (int i = 0; i < c1.length; i++) {
            a[i] = c1[c1.length - 1 - i] - '0';
        }
        for (int i = 0; i < c2.length; i++) {
            b[i] = c2[c2.length - 1 - i] - '0';
        }

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                result[i + j] += a[i] * b[j];
            }
        }
        for (int i = 0; i < result.length; i++) {
            if (result[i] >= 10) {
                result[i + 1] += result[i] / 10;
                result[i] %= 10;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for (int i = result.length - 1; i >= 0; i--) {
            if (result[i] != 0) {
                flag = true;
            }
            if (flag) {
                sb.append(result[i]);
            }
        }
        System.out.println(sb.toString());
    }

}
