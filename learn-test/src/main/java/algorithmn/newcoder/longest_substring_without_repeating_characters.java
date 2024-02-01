package algorithmn.newcoder;

import java.util.HashMap;
import java.util.Map;

/**
 * created at 2019/7/13
 * Given a string, find the length of the longest substring without repeating characters.
 * For example, the longest substring without repeating letters for "abcabcbb" is "abc",
 * which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
 *
 * @author sheyang
 */
public class longest_substring_without_repeating_characters {

    public static int lengthOfLongestSubstring(String s) {
        if (null == s || 0 == s.length()) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        int leftBound = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            leftBound = Math.max(leftBound, (map.containsKey(c)) ? map.get(c) + 1 : 0);
            //当前窗口长度
            max = Math.max(max, i - leftBound + 1);
            map.put(c, i);
        }
        return max;
    }

    public static void main(String[] args) {
        lengthOfLongestSubstring("abcabcbb");
    }

}
