package SwordOfferByNet;

/**
 * 第58题
 * 给定一个二叉树的某个结点，请找出中序遍历顺序的下一个结点并且返回
 *
 * @author qgl
 * @date 2017/08/30
 */
public class NextTreeNode58 {
    /**
     * 获取输入节点的下一个节点
     *
     * @param pNode
     * @return
     */
    public static TreeLinkNode getTreeLinkNextNode(TreeLinkNode pNode) {
        if (pNode == null) {
            return null;
        }
        if (pNode.right != null) {
            pNode = pNode.right;
            while (pNode.left != null) {
                pNode = pNode.left;
            }
            return pNode;
        }

        while (pNode.next != null) {
            // 找第一个当前节点是父节点左孩子的节点
            if (pNode.next.left == pNode)
                return pNode.next;
            pNode = pNode.next;
        }
        return null;
    }

    /**
     * 二叉树
     */
    public static class TreeLinkNode {
        public int val;
        public TreeLinkNode left = null;
        public TreeLinkNode right = null;
        public TreeLinkNode next = null;

        public TreeLinkNode(int val) {
            this.val = val;
        }
    }
}
