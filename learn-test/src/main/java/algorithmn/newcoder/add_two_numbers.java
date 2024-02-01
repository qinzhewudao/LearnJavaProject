package algorithmn.newcoder;

/**
 * created at 2019/7/13
 * You are given two linked lists representing two non-negative numbers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 *
 * @author sheyang
 */
public class add_two_numbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (null == l1) {
            return l2;
        }
        if (null == l2) {
            return l1;
        }
        ListNode result = new ListNode(-1);
        ListNode temp = result;
        Boolean overflow = false;
        while (null != l1 || null != l2 || overflow) {
            int value = (null == l1 ? 0 : l1.val) + (null == l2 ? 0 : l2.val);
            value = overflow ? value + 1 : value;
            if (9 < value) {
                value = value % 10;
                overflow = true;
            } else {
                overflow = false;
            }
            l1 = null == l1 ? null : l1.next;
            l2 = null == l2 ? null : l2.next;
            temp.next = new ListNode(value);
            temp = temp.next;
        }
        return result.next;
    }

}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}
