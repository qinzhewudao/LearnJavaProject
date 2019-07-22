package Algorithmn.leetcode;

import java.util.Stack;

/**
 * created at 2019/7/20
 *
 * @author sheyang
 */
public class leetcode20 {

    public boolean isValid(String s) {
        if(s.length() % 2 != 0) return false;
        Stack<Character> stack = new Stack<>();
        for (char alp : s.toCharArray()) {
            if (alp == '(') stack.push(')');
            else if (alp == '[') stack.push(']');
            else if (alp == '{') stack.push('}');
            else if (stack.isEmpty() || stack.pop() != alp) return false;
        }
        return stack.isEmpty();

    }

    public static void main(String[] args) {
        leetcode20 leetcode20 = new leetcode20();
        System.out.println(leetcode20.isValid("([])"));
    }


}
