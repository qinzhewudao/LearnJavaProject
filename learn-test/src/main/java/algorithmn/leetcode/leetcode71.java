package algorithmn.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author sheyang
 * @description
 * @date 2019/8/10
 * @time 下午6:31
 */
public class leetcode71 {

    public String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();
        String[] strings = path.split("/");
        for (String item : strings) {
            if ("..".equals(item)) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!item.isEmpty() && !".".equals(item)) {
                stack.push(item);
            }
        }
        StringBuilder res = new StringBuilder();
        for (String d : stack) {
            //从0开始插入
            res.insert(0, "/" + d);
        }
        return (res.length() == 0) ? "/" : res.toString();
    }

    public static void main(String[] args) {
        leetcode71 leetcode71 = new leetcode71();
        System.out.println(leetcode71.simplifyPath("/a/../../b/../c//.//"));
    }


}
