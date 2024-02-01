package Algorithmn.newcoder;

/**
 * created at 2019/7/13
 * Given a string S, find the longest palindromic substring in S.
 * You may assume that the maximum length of S is 1000,
 * and there exists one unique longest palindromic substring.
 *
 * @author sheyang
 */
public class longest_palindromic_substring {

    private int start;
    private int end;
    private int len;

    /**
     * 从中间往两边搜索
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        for (int i = 0; i < s.length(); i++) {
            //aba形式
            judge(s, i, i);
            //aa形式
            judge(s, i, i + 1);
        }
        return s.substring(start, end + 1);
    }


    public void judge(String s, int ss, int ee) {
        while (ss >= 0 && ee < s.length() && s.charAt(ss) == s.charAt(ee)) {
            ss--;
            ee++;
        }
        if (ee - ss - 1 > len) {
            start = ss + 1;
            end = ee - 1;
            len = ee - ss - 1;
        }
    }

    public static void main(String[] args) {
        longest_palindromic_substring palindromic_substring = new longest_palindromic_substring();
        String s = "aaabaaaa";
        System.out.println(palindromic_substring.longestPalindrome(s));
    }

}
