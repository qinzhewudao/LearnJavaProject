package Algorithmn.leetcode;

/**
 * created at 2019/7/20
 *
 * @author sheyang
 */
public class leetcode21 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode tmp = head;
        while(null != l1 || null != l2){
            int value1 = null == l1 ? Integer.MAX_VALUE : l1.val;
            int value2 = null == l2 ? Integer.MAX_VALUE : l2.val;
            if(value1 > value2){
                tmp.next = l2;
                l2 = l2.next;
            } else {
                tmp.next = l1;
                l1 = l1.next;
            }
            tmp = tmp.next;
        }
        return head.next;
    }

    public static void main(String[] args){
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        leetcode21 leetcode21 = new leetcode21();
        leetcode21.mergeTwoLists(l1,l2);

    }

}
