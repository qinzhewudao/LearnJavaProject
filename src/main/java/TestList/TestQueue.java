package TestList;

import java.util.LinkedList;
import java.util.Queue;

/**
 * created at 2019/6/30
 *
 * @author sheyang
 */
public class TestQueue {

    /**
     * 在 Queue 中 poll()和 remove()有什么区别？
     * <p>
     * 相同点：都是返回第一个元素，并在队列中删除返回的对象。
     * 不同点：如果没有元素 poll()会返回 null，而 remove()会直接抛出 NoSuchElementException 异常。
     *
     * @param args
     */
    public static void main(String[] args) {

        Queue<Integer> queue = new LinkedList<>();
        ((LinkedList<Integer>) queue).push(1);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.remove());

    }

}
