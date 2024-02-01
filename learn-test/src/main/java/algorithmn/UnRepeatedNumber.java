package algorithmn;

import java.util.ArrayList;
import java.util.List;

/**
 * created at 2019/7/6
 *
 * @author sheyang
 */
public class UnRepeatedNumber {

    public static void main(String[] args) {

        int num = 21738;
        List<Integer> list1 = new ArrayList<>();
        while (num != 0) {
            list1.add(num % 10);
            num /= 10;
        }


        for (int i = 0; i < list1.size() + 1; i++) {
            if (list1.get(i).equals(list1.get(i + 1))) {
                if (list1.get(i) == 9) {
//                    num +=
                } else {

                }

            }
        }

        System.out.println(list1);
    }

    private int mul(int n) {
        int result = 1;
        for (int i = 0; i < n; i++) {
            result *= 10;
        }
        return result;
    }


}
