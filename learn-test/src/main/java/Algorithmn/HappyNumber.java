package Algorithmn;

import java.util.HashSet;
import java.util.Set;

/**
 * created at 2019/6/23
 * /**
 * Function: 判断一个数字是否为快乐数字 19 就是快乐数字  11就不是快乐数字
 * 19
 * 1*1+9*9=82
 * 8*8+2*2=68
 * 6*6+8*8=100
 * 1*1+0*0+0*0=1
 * <p>
 * 11
 * 1*1+1*1=2
 * 2*2=4
 * 4*4=16
 * 1*1+6*6=37
 * 3*3+7*7=58
 * 5*5+8*8=89
 * 8*8+9*9=145
 * 1*1+4*4+5*5=42
 * 4*4+2*2=20
 * 2*2+0*0=2
 * <p>
 * 这里结果 1*1+1*1=2 和 2*2+0*0=2 重复，所以不是快乐数字
 *
 * @author sheyang
 */
public class HappyNumber {

    public static Boolean checkHappyNumber(Integer num) {

        Set<Integer> set = new HashSet<>();
        while (num != 1) {
            Integer sum = 0;
            while (num > 0) {
                sum += (num % 10) * (num % 10);
                num /= 10;
            }
            if (set.contains(sum)) {
                return false;
            } else {
                set.add(sum);
            }
            num = sum;
        }
        return true;

    }

    public static void main(String[] args) {
        System.out.println(checkHappyNumber(19));
        System.out.println(checkHappyNumber(11));
    }

}
