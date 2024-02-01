package SwordOfferByNet;


/**
 * 第62题
 * 二叉树的序列化与反序列化
 *
 * @author qgl
 * @date 2017/08/30
 */
public class Serializer62 {

    private static int index = -1;

    /**
     * 序列化二叉树 (前序遍历)
     *
     * @param root
     * @return
     */
    public static String serialize(LevelPrintTree61.TreeNode root) {
        StringBuffer sb = new StringBuffer();
        if (root == null) {
            sb.append("#,");
            return sb.toString();
        }

        sb.append(root.val + ",");
        sb.append(serialize(root.left));
        sb.append(serialize(root.right));
        return sb.toString();
    }

    /**
     * 反序列化二叉树
     *
     * @param str
     * @return
     */
    public static LevelPrintTree61.TreeNode deserialize(String str) {
        index++;
        int len = str.length();
        String[] strArray = str.split(",");
        LevelPrintTree61.TreeNode node = null;

        if (index >= len) {
            return null;
        }

        if (!strArray[index].equals("#")) {
            node = new LevelPrintTree61.TreeNode(Integer.valueOf(strArray[index]));
            node.left = deserialize(str);
            node.right = deserialize(str);
        }
        return node;
    }
}
