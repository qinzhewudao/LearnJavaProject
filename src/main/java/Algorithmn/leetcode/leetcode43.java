package Algorithmn.leetcode;

/**
 * created at 2019/7/21
 *
 * @author sheyang
 */
public class leetcode43 {

    public String multiply(String num1, String num2) {

        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        int length1 = num1.length();
        int length2 = num2.length();

        int[] res = new int[length1 + length2];

        for (int i = length1 - 1; i >= 0; i--) {
            for (int j = length2 - 1; j >= 0; j--) {
                res[i + j + 1] = res[i + j + 1] + (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            }
        }

        for (int i = res.length - 1; i >= 0; i--) {
            if (res[i] > 9) {
                res[i - 1] = res[i - 1] + res[i] / 10;
                res[i] = res[i] % 10;
            }
        }

        StringBuilder str = new StringBuilder();
        str.append(res[0] == 0 ? "" : res[0]);
        for (int i = 1; i < res.length; i++) {
            str.append(res[i]);
        }

        return str.toString();
    }

    public static void main(String[] args) {
        leetcode43 leetcode43 = new leetcode43();
        System.out.println(leetcode43.multiply("2", "3"));
    }

}
