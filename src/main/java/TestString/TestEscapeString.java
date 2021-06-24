package TestString;

import java.nio.charset.StandardCharsets;

/**
 * @author sheyang
 * @date 2021/6/24 10:23 上午
 */
public class TestEscapeString {

    /**
     * 中文转换为八进制串
     *
     * @param s
     * @return
     */
    public static String toOct(String s) {
        StringBuilder result = new StringBuilder();
        byte[] bytes = s.getBytes();
        for (byte b : bytes) {
            int b1 = b;
            if (b1 < 0) {
                b1 = 256 + b1;
            }
            result.append("\\").append((b1 / 64) % 8).append((b1 / 8) % 8).append(b1 % 8);
        }
        return result.toString();
    }

    /**
     * 八进制串转换为中文
     *
     * @param s
     * @return
     */
    public static String getOct(String s) {
        String[] as = s.split("\\\\");
        byte[] arr = new byte[as.length - 1];
        for (int i = 1; i < as.length; i++) {
            int sum = 0;
            int base = 64;
            for (char c : as[i].toCharArray()) {
                sum += base * ((int) c - '0');
                base /= 8;
            }
            if (sum >= 128) {
                sum = sum - 256;
            }
            arr[i - 1] = (byte) sum;
        }
        //如果还有乱码，这里编码方式你可以修改下，比如试试看unicode gbk等等
        return new String(arr, StandardCharsets.UTF_8);
    }


    public static void main(String[] args) {
        String transfer = toOct("独立寒秋，湘江北去");
        System.out.println(transfer);
        System.out.println(getOct(transfer));
    }

}
