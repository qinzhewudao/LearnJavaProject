package algorithmn.leetcode;

/**
 * created at 2019/7/21
 *
 * @author sheyang
 */
public class leetcode29 {

    public int divide(int dividend, int divisor) {

        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        int symbol = 1;
        if (dividend > 0) {
            dividend = -dividend;
        } else {
            symbol = -symbol;
        }
        if (divisor > 0) {
            divisor = -divisor;
        } else {
            symbol = -symbol;
        }

        int count = 0, tmpNumber, k;
        while (dividend <= divisor) {
            tmpNumber = divisor;
            k = 1;
            while (dividend <= tmpNumber + tmpNumber && tmpNumber + tmpNumber < 0) {
                tmpNumber += tmpNumber;
                k += k;
            }
            count = count + k;
            dividend -= tmpNumber;
        }
        return symbol > 0 ? count : -count;
    }

    public static void main(String[] args) {
        leetcode29 leetcode29 = new leetcode29();
        System.out.println(leetcode29.divide(-2147483648, -1));
    }

}
