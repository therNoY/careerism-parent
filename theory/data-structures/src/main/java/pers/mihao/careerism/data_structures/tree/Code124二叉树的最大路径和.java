package pers.mihao.careerism.data_structures.tree;


import pers.mihao.careerism.data_structures.util.TreeNode;

/**
 * 124. 二叉树中的最大路径和
 * 给定一个非空二叉树，返回其最大路径和。
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 * 示例 1:
 * 输入: [1,2,3]
 *  1
 * / \
 *2   3
 * 输出: 6
 * 示例 2:
 * 输入: [-10,9,20,null,null,15,7]
 * -10
 * / \
 *9   20
 *   /  \
 *  15   7
 * 输出: 42
 *
 * []
 */
public class Code124二叉树的最大路径和 {

    public static void main(String[] args) {
        System.out.println(new Code124二叉树的最大路径和().maxPathSum(TreeNode.getTreeByArray(5,4,8,11,null,13,4,7,2,null,null,null,1)));
        System.out.println(new Code124二叉树的最大路径和().maxPathSum210323(TreeNode.getTreeByArray(5,4,8,11,null,13,4,7,2,null,null,null,1)));
    }

    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        return Math.max(getMaxDept(root), max);
    }

    private int getMaxDept(TreeNode node) {
        if (node == null) return 0;
        int sum = node.val;
        int left = Math.max(getMaxDept(node.left), 0);
        int right = Math.max(getMaxDept(node.right), 0);
        max = Math.max(left + right + node.val, max);
        return Math.max(left, right) + node.val;
    }

    public int maxPathSum210323(TreeNode root) {
        return Math.max(getMaxDept210323(root), max);
    }

    private int getMaxDept210323(TreeNode node) {
        if (node == null) return 0;
        int left  = Math.max(getMaxDept210323(node.left), 0);
        int right  = Math.max(getMaxDept210323(node.right), 0);
        max = Math.max(left + right + node.val, max);
        return Math.max(left, right) + node.val;
    }

}
