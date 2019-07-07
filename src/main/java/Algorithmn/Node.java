package Algorithmn;

/**
 * created at 2019/7/6
 *
 * @author sheyang
 */
public class Node {
    int val;
    lruNode next;

    public Node(int val) {
        this.val = val;
    }

    /**
     * 单链表反转
     *
     * @param head
     * @return
     */
    public static lruNode reverseNode(lruNode head) {
        // 如果链表为空或只有一个节点，无需反转，直接返回原链表表头
        if (head == null || head.next == null) {
            return head;
        }

        lruNode reHead = null;
        lruNode cur = head;
        while (cur != null) {
            // 用reCur保存住对要处理节点的引用
            lruNode reCur = cur;
            // cur更新到下一个节点
            cur = cur.next;
            // 更新要处理节点的next引用
            reCur.next = reHead;
            // reHead指向要处理节点的前一个节点
            reHead = reCur;
        }
        return reHead;
    }

}
