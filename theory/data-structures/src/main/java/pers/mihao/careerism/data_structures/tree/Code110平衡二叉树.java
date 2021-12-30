package pers.mihao.careerism.data_structures.tree;

import pers.mihao.careerism.data_structures.util.TreeNode;

/**
 * 110. 平衡二叉树
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 * 示例 1:
 * 给定二叉树 [3,9,20,null,null,15,7]
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回 true 。
 * 示例 2:
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 * 1
 * / \
 * 2   2
 * / \
 * 3   3
 * / \
 * 4   4
 */
public class Code110平衡二叉树 {

    public static void main(String[] args) {
        System.out.println(new Code110平衡二叉树()
                .isBalanced(TreeNode.getTreeByArray(1, 2, 2, 3, null, null, 3, 4, null, null, 4)));
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        if (root.left == null && root.right == null) return true;
        if (root.left == null && (root.right.right != null || root.right.left != null)) return false;
        if (root.right == null && (root.left.right != null || root.left.left != null)) return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }

    private int getTreeHeight(TreeNode node) {
        if (node == null) return 0;
        return Math.max(getTreeHeight(node.left), getTreeHeight(node.right)) + 1;
    }

}
