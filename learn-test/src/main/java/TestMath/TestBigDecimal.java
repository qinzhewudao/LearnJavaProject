package TestMath;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * created at 2019/4/22
 *
 * @author sheyang
 */
public class TestBigDecimal {

    static int num = 5;

    public TestBigDecimal() {
    }

    public static void main(String[] args) {

        System.out.println(num);
        num++;
        System.out.println(num);
        System.out.println(0.2 + 0.1);
        System.out.println(0.3 - 0.1);
        System.out.println(0.2 * 0.1);
        System.out.println(0.3 / 0.1);

//        1.public BigDecimal(double val)    将double表示形式转换为BigDecimal *不建议使用
//
//　　2.public BigDecimal(int val)　　将int表示形式转换成BigDecimal
//
//　　3.public BigDecimal(String val)　　将String表示形式转换成BigDecimal
//
//        为什么不建议采用第一种构造方法呢？来看例子

        BigDecimal bigDecimal = new BigDecimal(2);
        BigDecimal bDouble = new BigDecimal(2.3);
        BigDecimal bString = new BigDecimal("2.3");
        System.out.println("bigDecimal=" + bigDecimal);
        System.out.println("bDouble=" + bDouble);
        System.out.println("bString=" + bString);

        //当double必须用作BigDecimal的源时，请使用Double.toString(double)转成String，
        // 然后使用String构造方法，或使用BigDecimal的静态方法valueOf，如下
        BigDecimal bDouble1 = BigDecimal.valueOf(2.3);
        BigDecimal bDouble2 = new BigDecimal(Double.toString(2.3));

        System.out.println("bDouble1=" + bDouble1);
        System.out.println("bDouble2=" + bDouble2);

        BigDecimal a = new BigDecimal("4.5");
        BigDecimal b = new BigDecimal("1.3");

        System.out.println("a + b =" + a.add(b));
        System.out.println("a - b =" + a.subtract(b));
        System.out.println("a * b =" + a.multiply(b));
        //其实divide方法有可以传三个参数
        //public BigDecimal divide(BigDecimal divisor, int scale, int roundingMode)
        //第一参数表示除数， 第二个参数表示小数点后保留位数，
        //第三个参数表示舍入模式，只有在作除法运算或四舍五入时才用到舍入模式，有下面这几种
        //复制代码
        //ROUND_CEILING    //向正无穷方向舍入
        //
        //ROUND_DOWN    //向零方向舍入
        //
        //ROUND_FLOOR    //向负无穷方向舍入
        //
        //ROUND_HALF_DOWN    //向（距离）最近的一边舍入，除非两边（的距离）是相等,如果是这样，向下舍入, 例如1.55 保留一位小数结果为1.5
        //
        //ROUND_HALF_EVEN    //向（距离）最近的一边舍入，除非两边（的距离）是相等,如果是这样，如果保留位数是奇数，使用ROUND_HALF_UP，如果是偶数，使用ROUND_HALF_DOWN
        //
        //ROUND_HALF_UP    //向（距离）最近的一边舍入，除非两边（的距离）是相等,如果是这样，向上舍入, 1.55保留一位小数结果为1.6
        //
        //ROUND_UNNECESSARY    //计算结果是精确的，不需要舍入模式
        //
        //ROUND_UP    //向远离0的方向舍入
        System.out.println("a / b =" + a.divide(b, 1, RoundingMode.HALF_UP));

        //*减乘除其实最终都返回的是一个新的BigDecimal对象，因为BigInteger与BigDecimal都
        // 是不可变的（immutable）的，在进行每一步运算时，都会产生一个新的对象
        BigDecimal aa = new BigDecimal("4.5");
        BigDecimal bb = new BigDecimal("1.5");
        aa.add(bb);

        System.out.println(aa);
    }

}
