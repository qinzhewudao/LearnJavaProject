package Algorithmn;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * created at 2019/6/22
 *
 * @author sheyang
 */
public class Tree<AnyType extends Comparable<? super AnyType>> {
    private static class BinaryNode<AnyType> {

        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }

        AnyType element;
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;
    }

    private BinaryNode<AnyType> root;

    public void insert(AnyType x) {
        root = insert(x, root);
    }

    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
        if (t == null) {
            return new BinaryNode<>(x, null, null);
        }

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
        }

        return t;
    }

    /**
     * 前序遍历
     */
    public void preOrder(BinaryNode<AnyType> node) {
        if (node != null) {
            System.out.print(node.element + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     * 中序遍历
     */
    public void midOrder(BinaryNode<AnyType> node) {
        if (node != null) {
            midOrder(node.left);
            System.out.print(node.element + " ");
            midOrder(node.right);
        }
    }

    /**
     * 后序遍历
     */
    public void posOrder(BinaryNode<AnyType> node) {
        if (node != null) {
            posOrder(node.left);
            posOrder(node.right);
            System.out.print(node.element + " ");
        }
    }

    /**
     * 层序遍历
     * 递归
     */
    public void levelOrder(BinaryNode<AnyType> node) {
        if (node == null) {
            return;
        }

        int depth = depth(node);

        for (int i = 1; i <= depth; i++) {
            levelOrder(node, i);
        }
    }

    private void levelOrder(BinaryNode<AnyType> node, int level) {
        if (node == null || level < 1) {
            return;
        }

        if (level == 1) {
            System.out.print(node.element + "  ");
            return;
        }

        // 左子树
        levelOrder(node.left, level - 1);

        // 右子树
        levelOrder(node.right, level - 1);
    }

    public int depth(BinaryNode<AnyType> node) {
        if (node == null) {
            return 0;
        }

        int l = depth(node.left);
        int r = depth(node.right);
        if (l > r) {
            return l + 1;
        } else {
            return r + 1;
        }
    }

    /**
     * 前序遍历
     * 非递归
     */
    public void preOrder1(BinaryNode node) {
        Stack<BinaryNode> stack = new Stack<>();
        while (node != null || !stack.empty()) {
            while (node != null) {
                System.out.print(node.element + "   ");
                stack.push(node);
                node = node.left;
            }
            if (!stack.empty()) {
                node = stack.pop();
                node = node.right;
            }
        }
    }

    /**
     * 中序遍历
     * 非递归
     */
    public void midOrder1(BinaryNode node) {
        Stack<BinaryNode> stack = new Stack<>();
        while (node != null || !stack.empty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.empty()) {
                node = stack.pop();
                System.out.print(node.element + "   ");
                node = node.right;
            }
        }
    }

    /**
     * 后序遍历
     * 非递归
     */
    public void posOrder1(BinaryNode node) {
        Stack<BinaryNode> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        int i = 1;
        while (node != null || !stack1.empty()) {
            while (node != null) {
                stack1.push(node);
                stack2.push(0);
                node = node.left;
            }

            while (!stack1.empty() && stack2.peek() == i) {
                stack2.pop();
                System.out.print(stack1.pop().element + "   ");
            }

            if (!stack1.empty()) {
                stack2.pop();
                stack2.push(1);
                node = stack1.peek();
                node = node.right;
            }
        }
    }

    private void posOrder2(BinaryNode node) {
        if (null == node) {
            return;
        }
        Stack<BinaryNode> stack = new Stack<>();
        //记录上次访问的节点
        BinaryNode lastVisit = null;

        while (node != null || !stack.empty()) {
            //先把pCur移动到左子树最下边
            while (null != node) {
                stack.push(node);
                node = node.left;
            }
            // 左子树节点遍历后不能对直接取栈顶元素进行pop操作，需要对其右子树节点进行判断，
            // 这里需要记录上次访问的节点prevNode,右子树节点为空或右子树节点为上次访问过的节点，
            // 都说明此子树已经遍历完成。
            BinaryNode top = stack.peek();
            if (null == top.right || top.right == lastVisit) {
                System.out.print(top.element + " ");
                //修改最近被访问的节点
                lastVisit = top;

                stack.pop();

            } else {
                //进入右子树，且可肯定右子树一定不为空
                node = top.right;
            }

        }
    }

    /**
     * 层序遍历
     * 非递归
     *
     * @param node
     */
    public void levelOrder1(BinaryNode<AnyType> node) {
        if (node == null) {
            return;
        }

        BinaryNode binaryNode;
        Queue<BinaryNode> queue = new LinkedList<>();
        queue.add(node);

        while (queue.size() != 0) {

            binaryNode = queue.poll();

            if (null == binaryNode) {
                return;
            }

            System.out.print(binaryNode.element + "   ");

            if (binaryNode.left != null) {
                queue.offer(binaryNode.left);
            }
            if (binaryNode.right != null) {
                queue.offer(binaryNode.right);
            }
        }
    }

    public static void main(String[] args) {
        int[] input = {4, 2, 6, 1, 3, 5, 7, 8, 10};
        Tree<Integer> tree = new Tree<>();
        for (int anInput : input) {
            tree.insert(anInput);
        }
        System.out.print("递归前序遍历 ：");
        tree.preOrder(tree.root);
        System.out.print("\n非递归前序遍历：");
        tree.preOrder1(tree.root);
        System.out.print("\n递归中序遍历 ：");
        tree.midOrder(tree.root);
        System.out.print("\n非递归中序遍历 ：");
        tree.midOrder1(tree.root);
        System.out.print("\n递归后序遍历 ：");
        tree.posOrder(tree.root);
        System.out.print("\n非递归后序遍历 ：");
        tree.posOrder2(tree.root);
        System.out.print("\n递归层序遍历：");
        tree.levelOrder(tree.root);
        System.out.print("\n非递归层序遍历 ：");
        tree.levelOrder1(tree.root);


    }
}