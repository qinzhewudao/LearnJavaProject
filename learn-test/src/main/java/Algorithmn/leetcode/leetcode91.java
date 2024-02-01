package Algorithmn.leetcode;

/**
 * created at 2019/7/27
 *
 * @author sheyang
 */
public class leetcode91 {

    public int numDecodings(String s) {

        if (s.charAt(0) == '0') {
            return 0;
        }

        int first = 1;
        int second = 1;
        int third = 1;

        for (int i = 2; i <= s.length(); i++) {
            third = 0;
            if (s.charAt(i - 1) != '0') {
                third += second;
            }

            if ((s.charAt(i - 2) == '1') || (s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6')) {
                third += first;
            }

            first = second;
            second = third;
        }

        return third;
    }

    public static void main(String[] args) {
        leetcode91 leetcode91 = new leetcode91();
        System.out.println(leetcode91.numDecodings("226"));
    }

}
