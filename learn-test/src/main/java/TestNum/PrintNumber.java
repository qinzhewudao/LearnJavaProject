package TestNum;

/**
 * @author sheyang
 * @description
 * @date 2019/9/8
 * @time 下午7:22
 */
public class PrintNumber {

    private static void printNumber(int number) {
        /*
          0x80000000是数的十六进制表示，转成二进制表示为10000000000000000000000000000000

          运算的优先级，移位运算高于逻辑运算，>>>高于&

          位逻辑与运算 1&1 = 1 ，0&1 = 0

          >>>无符号右移，移出部分舍弃，左边位补0；
         */
        for (int i = 0; i < 32; i++) {
            int t = (number & 0x80000000 >>> i) >>> (31 - i);
            System.out.print(t);
        }
        System.out.println();
    }

    private static boolean isPowerOfTwo(int val) {
        return (val & (val-1)) == 0;
    }

    private static boolean isPowerOfTwo2(int val) {
        return (val & -val) == val;
    }


    /**
     * 打印数字的二进制表示
     *
     * @param args
     */
    public static void main(String[] args) {
        int a = 1120429670;
        printNumber(a);
        printNumber(-a);

        System.out.println(isPowerOfTwo2(128));
    }

}
