package Algorithmn.leetcode;

import java.util.*;

/**
 * created at 2019/7/21
 *
 * @author sheyang
 */
public class leetcode30 {

    public List<Integer> findSubstring(String s, String[] words) {

        int listLen = words.length;
        if (0 == listLen) {
            return new ArrayList<>();
        }

        int strLen = s.length();
        int wordLen = words[0].length();
        if (strLen < listLen * wordLen) {
            return new ArrayList<>();
        }

        Map<String, Integer> allWords = new HashMap<>(listLen);
        for (String word : words) {
            allWords.put(word, allWords.getOrDefault(word, 0) + 1);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < wordLen; i++) {
            for (int j = i; j <= strLen - wordLen * listLen; j += wordLen) {
                Map<String, Integer> tmpWords = new HashMap<>(listLen);
                for (int k = listLen - 1; k >= 0; k--) {
                    String temp = s.substring(j + k * wordLen, j + (k + 1) * wordLen);
                    int val = tmpWords.getOrDefault(temp, 0) + 1;
                    if (val > allWords.getOrDefault(temp, 0)) {
                        j += k * wordLen;
                        break;
                    }
                    if (0 == k) {
                        result.add(j);
                    } else {
                        tmpWords.put(temp, val);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.sort(Comparator.comparingInt(a -> a));
        leetcode30 leetcode30 = new leetcode30();
        System.out.println(leetcode30.findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
    }

}
