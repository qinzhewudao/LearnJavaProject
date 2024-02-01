package TestString;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Random;

/**
 * @author: sheyang
 * @date: 2020/7/7 11:17 上午
 */
public class RandomChinese {

    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            String chineseName = getRandomJianHan(new Random().nextInt(2) + 3);
            set.add(chineseName);
        }

        for (String s : set) {
            System.err.print(s + "\n");
        }
    }

    private static String getRandomJianHan(int len) {

        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < len; i++) {
            String str = null;
            // 定义高低位
            int highPos, lowPos;
            Random random = new Random();
            // 获取高位值
            highPos = (176 + Math.abs(random.nextInt(39)));
            // 获取低位值
            lowPos = (161 + Math.abs(random.nextInt(93)));
            byte[] b = new byte[2];
            b[0] = (new Integer(highPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try {
                // 转成中文
                str = new String(b, "GBK");
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            ret.append(str);
        }
        return ret.toString();
    }

}

