package pers.mihao.careerism.data_structures.tree;

import java.util.Deque;
import java.util.LinkedList;
import pers.mihao.careerism.data_structures.util.TreeNode;

/**
 * 99. 恢复二叉搜索树
 * 给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 * 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？
 * 示例 1：
 * 输入：root = [1,3,null,null,2]
 * 输出：[3,1,null,null,2]
 * 解释：3 不能是 1 左孩子，因为 3 > 1 。交换 1 和 3 使二叉搜索树有效。
 * 示例 2：
 * 输入：root = [3,1,4,null,null,2]
 * 输出：[2,1,4,null,null,3]
 * 解释：2 不能在 3 的右子树中，因为 2 < 3 。交换 2 和 3 使二叉搜索树有效。
 * @Author mihao
 * @Date 2021/3/29 11:25
 */
public class Code99恢复二叉搜索数 {

    public static void main(String[] args) {
        TreeNode.getTreeByArray(1,3,null,null,2).print();
        new Code99恢复二叉搜索数().recoverTree(TreeNode.getTreeByArray(1,3,null,null,2));
        TreeNode.getTreeByArray(3,1,null,null,2).print();

//        new Code99恢复二叉搜索数().recoverTree(TreeNode.getTreeByArray(3,1,4,null,null,2));
    }

    /**
     * 先序遍历
     * @param root
     */
    public void recoverTree(TreeNode root) {
        inOrder(root);
        root.print();
    }

    TreeNode preVisitNode;

    void inOrder(TreeNode node) {
        if (node == null) return;
        inOrder(node.left);
        if (preVisitNode == null) preVisitNode = node;
        if (node.val < preVisitNode.val) {
            int temp = node.val;
            node.val = preVisitNode.val;
            preVisitNode.val = temp;
        }
        preVisitNode = node;
        inOrder(node.right);
    }


    public void recoverTree20210331(TreeNode root) {
        TreeNode err0 = null;
        TreeNode err1 = null;
        TreeNode err2 = null;
        TreeNode pre = null;
        TreeNode curr = null;
        if (root == null) return;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        while (deque.size() > 0 && err2 == null) {
            TreeNode node = deque.pollLast();
            if (node != null) {
                if (node.right != null) deque.addLast(node.right);
                deque.addLast(node);
                deque.addLast(null);
                if (node.left != null) deque.addLast(node.left);
            } else {
                node = deque.pollLast();
                pre = curr;
                curr = node;
                if (pre != null && curr.val < pre.val) {
                    if (err1 == null) {
                        err1 = pre;
                        err0 = curr;
                    }
                    else err2 = curr;
                }
            }
        }
        if (err2 == null) err2 = err0;
        int temp = err1.val;
        err1.val = err2.val;
        err2.val = temp;
    }

    public void recoverTree20211229(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        TreeNode big = null, small = null, node = root, preNode = root;
        Integer lastNum = null, temp;
        while (node != null || !deque.isEmpty()) {
            while (node != null) {
                deque.addFirst(node);
                node = node.left;
            }
            node = deque.pollFirst();
            if (lastNum == null) {
                lastNum = node.val;
            } else {
                if (big == null && lastNum > node.val) {
                    big = preNode;
                }
                if (node.val < lastNum) {
                    small = node;
                }
                lastNum = node.val;
            }
            preNode = node;
            node = node.right;
        }
        temp = big.val;
        big.val = small.val;
        small.val = temp;
    }


}
