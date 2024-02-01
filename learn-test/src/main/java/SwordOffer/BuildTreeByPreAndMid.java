package SwordOffer;

import java.util.Arrays;

/**
 * created at 2019/6/30
 *
 * @author sheyang
 */
public class BuildTreeByPreAndMid {

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] mid = {4, 7, 2, 1, 5, 3, 8, 6};
        BuildTreeByPreAndMid buildTreeByPreAndMid = new BuildTreeByPreAndMid();
        TreeNode treeNode = buildTreeByPreAndMid.reConstructBinaryTree(pre, mid);
        buildTreeByPreAndMid.printPreOrder(treeNode);

    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null) {
            return null;
        }
        if (pre.length == 0 || in.length == 0) {
            return null;
        }
        if (pre.length != in.length) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        for (int i = 0; i < pre.length; i++) {
            if (pre[0] == in[i]) {
                root.left = reConstructBinaryTree(
                        Arrays.copyOfRange(pre, 1, i + 1),
                        Arrays.copyOfRange(in, 0, i));
                root.right = reConstructBinaryTree(
                        Arrays.copyOfRange(pre, i + 1, pre.length),
                        Arrays.copyOfRange(in, i + 1, in.length));
            }
        }
        return root;
    }

    public void printPreOrder(TreeNode treeNode) {

        if (null == treeNode) {
            return;
        }

        System.out.println(treeNode.val);

        printPreOrder(treeNode.left);

        printPreOrder(treeNode.right);


    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
