package SwordOffer;

import java.util.Stack;

/**
 * created at 2019/6/30
 *
 * @author sheyang
 */
public class TwoStackSimQueue<T> {

    private Stack<T> stack1 = new Stack<>();

    private Stack<T> stack2 = new Stack<>();


    public void push(T node) {
        stack1.push(node);
    }

    public T pop() throws Exception {
        if (stack1.isEmpty() && stack2.isEmpty()) {
            throw new Exception("栈为空！");
        }

        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

}
