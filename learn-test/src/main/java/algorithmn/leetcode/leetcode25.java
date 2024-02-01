package algorithmn.leetcode;

/**
 * created at 2019/7/21
 *
 * @author sheyang
 */
public class leetcode25 {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode[] tempNodes = new ListNode[k + 1];
        tempNodes[0] = head;
        //把当前k+1个结点存入栈中
        for (int i = 1; i < k + 1; i++) {
            //结束翻转
            if (tempNodes[i - 1] == null) {
                return head;
            }
            //依次存入
            tempNodes[i] = tempNodes[i - 1].next;
        }
        //翻转链表
        for (int j = k - 1; j > 0; j--) {
            tempNodes[j].next = tempNodes[j - 1];
        }
        //递归后续节点
        tempNodes[0].next = reverseKGroup(tempNodes[k], k);
        //返回新的头节点
        return tempNodes[k - 1];
    }

}
