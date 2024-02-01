package Algorithmn.leetcode;

import SwordOfferByNet.LevelPrintTree61.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * created at 2019/7/27
 *
 * @author sheyang
 */
public class leetcode95 {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>(0);
        }
        return buildTree(1, n);

    }

    private List<TreeNode> buildTree(int start, int end) {
        List<TreeNode> ans = new ArrayList<>();
        //此时没有数字，将 null 加入结果中
        if (start > end) {
            ans.add(null);
            return ans;
        }
        //只有一个数字，当前数字作为一棵树加入结果中
        if (start == end) {
            ans.add(new TreeNode(start));
            return ans;
        }
        //尝试每个数字作为根节点
        for (int i = start; i <= end; i++) {
            //得到所有可能的左子树
            List<TreeNode> leftTrees = buildTree(start, i - 1);
            //得到所有可能的右子树
            List<TreeNode> rightTrees = buildTree(i + 1, end);
            //左子树右子树两两组合
            for (TreeNode leftTree : leftTrees) {
                for (TreeNode rightTree : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftTree;
                    root.right = rightTree;
                    //加入到最终结果中
                    ans.add(root);
                }
            }
        }
        return ans;
    }

}
