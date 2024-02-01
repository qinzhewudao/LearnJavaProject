package algorithmn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author sheyang
 * @date 2021/9/1 3:56 下午
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public void printLeverOrderIte(TreeNode root) {
        if (null == root) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + " ");
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        System.out.println();
    }

    public List<List<Integer>> levelOrderRec(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        levelOrderRec(res, root, 0);
        return res;
    }

    private void levelOrderRec(List<List<Integer>> list, TreeNode root, int level) {
        //终止条件
        if (null == root) {
            return;
        }
        //level表示的是层数，如果level >= list.size()，说明到下一层了，所以
        //要先把下一层的list初始化，防止下面add的时候出现空指针异常
        //这里访问到第几层，我们就把数据加入到第几层
        List<Integer> currentList;
        if (level == list.size()) {
            currentList = new ArrayList<>();
            list.add(currentList);
        } else {
            currentList = list.get(level);
        }
        currentList.add(root.val);
        //当前节点访问完之后，再使用递归的方式分别访问当前节点的左右子节点
        levelOrderRec(list, root.left, level + 1);
        levelOrderRec(list, root.right, level + 1);
    }

    public List<List<Integer>> levelOrderIte(TreeNode root) {
        if (null == root) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int currentLevelSize = queue.size();
            for (int i = 0; i < currentLevelSize; ++i) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(level);
        }
        return result;
    }

    public boolean isSymmetricRec(TreeNode root) {
        if (null == root) {
            return true;
        }
        //从两个子节点开始判断
        return isSymmetricRec(root.left, root.right);
    }

    private boolean isSymmetricRec(TreeNode left, TreeNode right) {
        //如果左右子节点都为空，说明当前节点是叶子节点，返回true
        if (left == null && right == null) {
            return true;
        }
        //如果当前节点只有一个子节点或者有两个子节点，但两个子节点的值不相同，直接返回false
        if (left == null || right == null || left.val != right.val) {
            return false;
        }
        //然后左子节点的左子节点和右子节点的右子节点比较，左子节点的右子节点和右子节点的左子节点比较
        return isSymmetricRec(left.left, right.right) && isSymmetricRec(left.right, right.left);
    }

    public boolean isSymmetricIte(TreeNode root) {
        if (null == root) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        while (!queue.isEmpty()) {
            //每两个出队
            TreeNode left = queue.poll(), right = queue.poll();
            //如果都为空继续循环
            if (null == left && null == right) {
                continue;
            }
            if (null == left ^ null == right) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }
            queue.add(left.left);
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);
        }
        return true;
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{1, 2, 2, 3, 4, 4, 3};
        TreeNode treeNode = buildTree(a);
        treeNode.printLeverOrderIte(treeNode);
        System.out.println(treeNode.levelOrderRec(treeNode));
        System.out.println(treeNode.levelOrderIte(treeNode));
        System.out.println(treeNode.isSymmetricIte(treeNode));
        System.out.println(treeNode.isSymmetricRec(treeNode));
    }

    public static TreeNode buildTree(Integer[] data) {
        if (null == data || 0 == data.length) {
            return new TreeNode();
        }
        //层序遍历第一个就是根
        TreeNode root = new TreeNode(data[0]);
        //数组下标
        int index = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (index < data.length) {
            TreeNode tempNode = queue.poll();
            Integer tempValue = data[index++];
            //左节点不为空
            if (null != tempValue) {
                TreeNode node = new TreeNode(tempValue);
                tempNode.left = node;
                queue.offer(node);
            }
            tempValue = data[index++];
            //右节点不为空
            if (null != tempValue) {
                TreeNode node = new TreeNode(tempValue);
                tempNode.right = node;
                queue.offer(node);
            }
        }
        return root;
    }

}


