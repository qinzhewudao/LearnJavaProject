package Algorithmn.newcoder;

/**
 * created at 2019/7/13
 * z字行变换
 * <p>
 * Example 1:
 * <p>
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * 1
 * 2
 * Example 2:
 * <p>
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * 1
 * 2
 * Explanation:
 * <p>
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 *
 * @author sheyang
 */
public class zigzag_conversion {

    public String convert(String s, int nRows) {
        if (s == null || nRows >= s.length() || nRows <= 1) {
            return s;
        }
        char[] res = new char[s.length()];
        int groupCount = (nRows << 1) - 2;
        int i = 0;
        //处理每一行
        for (int row = 0; row < nRows; row++) {
            for (int index = row, secIndex = groupCount - row; index < s.length();
                 index += groupCount, secIndex += groupCount) {
                res[i++] = s.charAt(index);
                if (row > 0 && row < nRows - 1 && secIndex < s.length()) {
                    res[i++] = s.charAt(secIndex);
                }
            }
        }
        return new String(res);

//        StringBuilder[] sb = new StringBuilder[nRows];
//        for (int i = 0; i < nRows; i++) {
//            sb[i] = new StringBuilder();
//        }
//
//        int len = s.length();
//        int i = 0;
//        while (i < len) {
//            for (int j = 0; j < nRows && i < len; j++) {
//                sb[j].append(s.charAt(i++));
//            }
//            for (int j = nRows - 2; j > 0 && i < len; j--) {
//                sb[j].append(s.charAt(i++));
//            }
//        }
//        for (int j = 1; j < nRows; j++) {
//            sb[0].append(sb[j]);
//        }
//        return sb[0].toString();
    }

    public static void main(String[] args) {
        zigzag_conversion zigzag_conversion = new zigzag_conversion();
        System.out.println(zigzag_conversion.convert("PAYPALISHIRING", 3));
    }

}
