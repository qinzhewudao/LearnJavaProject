package SwordOfferByNet;

/**
 * 第57题
 * 删除链表的重复节点
 *
 * @author qgl
 * @date 2017/08/30
 */
public class DeleteDuplication57 {
    public static ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null) {
            return null;
        }
        // 新建一个节点，防止头结点被删除
        ListNode first = new ListNode(-1);
        first.next = pHead;
        ListNode p = pHead;
        // 指向前一个节点
        ListNode preNode = first;

        while (p != null && p.next != null) {
            if (p.val == p.next.val) {
                int val = p.val;
                // 向后重复查找
                while (p != null && p.val == val) {
                    p = p.next;
                }
                // 赋值:相当于删除
                preNode.next = p;
            } else {
                // 如果当前节点和下一个节点值不等，则向后移动一位
                preNode = p;
                p = p.next;
            }
        }
        return first.next;
    }

    public static class ListNode {
        public int val;
        public ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
