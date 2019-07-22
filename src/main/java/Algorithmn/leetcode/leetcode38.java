package Algorithmn.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * created at 2019/7/21
 *
 * @author sheyang
 */
public class leetcode38 {
    public String countAndSay(int n) {
        String str = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder builder = new StringBuilder();
            char pre = str.charAt(0);
            int count = 1;
            for (int j = 1; j < str.length(); j++) {
                char c = str.charAt(j);
                //当前字符与之前字符相同，进行计数
                if (c == pre) {
                    count++;
                } else {
                    builder.append(count).append(pre);
                    pre = c;
                    count = 1;
                }
            }
            //注意别忘记添加最后一次记录的数据
            builder.append(count).append(pre);
            str = builder.toString();
        }
        return str;
    }

    public static void main(String[] args){
        leetcode38 leetcode38 = new leetcode38();
        System.out.println(leetcode38.countAndSay(4));
    }

}
