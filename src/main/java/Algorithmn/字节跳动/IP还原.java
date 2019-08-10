package Algorithmn.字节跳动;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sheyang
 * @description
 * @date 2019/8/10
 * @time 下午3:19
 */
public class IP还原 {

    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        int[] tempIP = new int[4];
        dfs(result, s, tempIP, 0);
        return result;
    }

    public void dfs(List<String> result, String s, int[] tempIP, int segmentIndex) {
        if (segmentIndex >= 3) {
            String str4 = s.substring(tempIP[3]);
            if (isLessOrEqual255(str4)) {
                String builder = s.substring(tempIP[0], tempIP[1]) + "." +
                        s.substring(tempIP[1], tempIP[2]) + "." +
                        s.substring(tempIP[2], tempIP[3]) + "." +
                        str4;
                result.add(builder);
            }
            return;
        }
        int curStart = tempIP[segmentIndex];
        int curEnd = curStart + 3;
        for (int i = curStart; i < curEnd && i < s.length(); i++) {
            if (isLessOrEqual255(s.substring(curStart, i + 1))) {
                tempIP[segmentIndex + 1] = i + 1;
                dfs(result, s, tempIP, segmentIndex + 1);
            }
        }
    }

    /**
     * 判断字符串代表的 IP 段是不是符合要求
     *
     * @param s
     * @return
     */
    private boolean isLessOrEqual255(String s) {
        // s 的长度大于 1 小于 4
        // 以 0 开头使只能是 "0"
        // 长度小于 3 且不为 0 开头
        // 长度为 3 时要小于 255 且不为 0 开头
        if (s.length() < 1) {
            return false;
        } else if (s.startsWith("0") && s.length() == 1) {
            return true;
        } else if (s.length() < 3 && !s.startsWith("0")) {
            return true;
        } else {
            return s.length() == 3 && !s.startsWith("0") && s.compareTo("255") <= 0;
        }
    }

    public static void main(String[] args){
        IP还原 ip还原 = new IP还原();
        System.out.println(ip还原.restoreIpAddresses("25525511135"));
    }


    // cur : 当前答案，以 String List的形式，最后再join成String形式 例如 [[255],[255],[111],[35]] -> 255.255.111.35
    // pos, 当前扫描到的s的位置， ans最终答案
    private void backtracking(String s, int pos, List<String> cur,  List<String> ans) {
        if (cur.size() >= 4) {
            if (pos == s.length()) {
                ans.add(String.join(".", cur));
            }
            return;
        }
        // 分割得到ip地址的一段后，下一段只能在长度1-3范围内选择
        for (int i = 1; i <= 3; i++) {
            if (pos+i > s.length()) {
                break;
            }
            String segment = s.substring(pos, pos+i);
            // 剪枝条件：不能以0开头，不能大于255
            if (segment.startsWith("0") && segment.length() > 1 || (i == 3 && Integer.parseInt(segment) > 255)) {
                continue;
            }
            cur.add(segment);
            // 注意此处传的参数
            backtracking(s, pos+i, cur, ans);
            cur.remove(cur.size()-1);
        }
    }
    public List<String> restoreIpAddresses2(String s) {
        List<String> ans = new ArrayList<>();
        backtracking(s, 0, new ArrayList<>(), ans);
        return ans;
    }

}
