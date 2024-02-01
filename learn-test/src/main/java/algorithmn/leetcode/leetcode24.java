package algorithmn.leetcode;

/**
 * created at 2019/7/21
 *
 * @author sheyang
 */
public class leetcode24 {

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = new ListNode(-1);
        pre.next = head;
        ListNode tmp = pre;

        while (tmp.next != null && tmp.next.next != null) {
            //找到交换的两个节点
            ListNode start = tmp.next;
            ListNode end = tmp.next.next;
            //第一次遍历的时候，tmp等于pre，此时将pre.NEXT设为了调整后的头节点，
            // 以后tmp会往后移动，不等于pre，所以不会再修改pre.NEXT
            tmp.next = end;
            //交换两个节点
            start.next = end.next;
            end.next = start;
            //tmp移到下两个要交换的节点前一个
            tmp = start;
        }

        return pre.next;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        leetcode24 leetcode24 = new leetcode24();
        System.out.println(leetcode24.swapPairs(node));
    }


}
