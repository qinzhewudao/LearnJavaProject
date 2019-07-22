package Algorithmn.leetcode;

/**
 * created at 2019/7/20
 *
 * @author sheyang
 */
public class leetcode23 {

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(0);
        ListNode tmp = head;
        Boolean end = true;
        int n = lists.length;
        if (0 >= n) {
            return null;
        }
        if (1 == n) {
            return lists[0];
        }
        int[] values = new int[n];

        while (end) {
            end = false;
            int minValue = Integer.MAX_VALUE;
            int minIndex = n - 1;
            for (int i = 0; i < n; i++) {
                if (null != lists[i]) {
                    values[i] = lists[i].val;
                    end = true;
                    if (minValue >= values[i]) {
                        minValue = values[i];
                        minIndex = i;
                    }
                } else {
                    values[i] = Integer.MAX_VALUE;
                }
            }
            if (end) {
                tmp.next = lists[minIndex];
                lists[minIndex] = lists[minIndex].next;
            }
        }
        return head.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        leetcode23 leetcode23 = new leetcode23();

        leetcode23.mergeKLists(new ListNode[]{l1, l2});
    }

}
