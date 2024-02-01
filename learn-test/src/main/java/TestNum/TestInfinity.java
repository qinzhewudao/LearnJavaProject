package TestNum;

import java.math.BigDecimal;

/**
 * author sheyang
 * created at 2018/9/3
 */
public class TestInfinity {

    public static void main(String[] args) {
        System.out.println("***************获取POSITIVE-INFINITY/NEGATIVE-INFINITY******************");
        System.out.println("POSITIVE_INFINITY(1.0/0.0):" + Double.POSITIVE_INFINITY);
        System.out.println("NEGATIVE_INFINITY(-1.0/0.0):" + Double.NEGATIVE_INFINITY);
        System.out.println("POSITIVE-INFINITY/NEGATIVE-INFINITY:" + Double.POSITIVE_INFINITY / Double.NEGATIVE_INFINITY);
        System.out.println("POSITIVE_INFINITY - 1:" + (Double.POSITIVE_INFINITY - 1));

        System.out.println(2.0 - 1.1);
        System.out.println(1 / 10);
        System.out.println(BigDecimal.valueOf(1.0).divide(BigDecimal.valueOf(10.0)));

        //错误方法
        BigDecimal one1 = new BigDecimal(1.34);//1.3400000000000000799360577730112709105014801025390625
        System.out.println(one1);
        //正确方法1
        BigDecimal two1 = new BigDecimal("1.34");//1.34
        System.out.println(two1);
        //正确方法2
        BigDecimal two2 = new BigDecimal(Double.toString(1.34));//1.34
        System.out.println(two2);


//        public BigDecimal add(BigDecimal value);//加法
//        public BigDecimal subtract(BigDecimal value);//减法
//        public BigDecimal multiply(BigDecimal value);//乘法
//        public BigDecimal divide(BigDecimal value);//除法


    }
}
