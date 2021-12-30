package pers.mihao.careerism.data_structures.tree;

import pers.mihao.careerism.data_structures.util.TreeNode;

/**
 * 112. 路径总和
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。
 * 判断该树中是否存在 根节点到叶子节点 的路径，
 * 这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
 *
 * 叶子节点 是指没有子节点的节点。
 * @version 1.0
 * @auther mihao
 * @date 2021\12\30 0030 20:21
 */
public class Code112路径总和 {

    public boolean hasPathSum(TreeNode root, int sum) {
        return hasPathSum(root.left, sum, root.val) || hasPathSum(root.right, sum, root.val);
    }

    public boolean hasPathSum(TreeNode node, int sum, int currentSum) {
        if (node == null) return false;
        currentSum = currentSum + node.val;
        if (node.right == null && node.left == null) {
            if (currentSum == sum) return true;
        }
        return hasPathSum(node.left, sum, currentSum) || hasPathSum(node.right, sum, currentSum);
    }

    public boolean hasPathSum20211230(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.right == null && root.left == null) return root.val == targetSum;
        return hasPathSum20211230(root.left, targetSum - root.val)
                || hasPathSum20211230(root.right, targetSum - root.val);
    }

}
