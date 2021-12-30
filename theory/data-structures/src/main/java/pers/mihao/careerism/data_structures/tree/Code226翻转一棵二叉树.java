package pers.mihao.careerism.data_structures.tree;

import pers.mihao.careerism.data_structures.mytree.Tree;
import pers.mihao.careerism.data_structures.util.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @auther mihao
 * @date 2021\12\30 0030 21:46
 */
public class Code226翻转一棵二叉树 {

    public static void main(String[] args) {
        new Code226翻转一棵二叉树().invertTree(TreeNode.getTreeByArray(4,2,7,1,3,6,9)).print();
    }


    private TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        TreeNode tempNode;
        tempNode = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(tempNode);
        return root;
    }

    public TreeNode invertTreeByDeque(TreeNode root) {
        if (root == null) return root;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        TreeNode tempNode;
        while (deque.size() > 0) {
            TreeNode treeNode = deque.pollLast();
            tempNode = treeNode.left;
            treeNode.left = treeNode.right;
            treeNode.right = tempNode;

            if (treeNode.left != null) deque.addLast(treeNode.left);
            if (treeNode.right != null) deque.addLast(treeNode.right);
        }
        return root;
    }


    public TreeNode invertTree20211230(TreeNode root) {
        if (root == null) return null;
        if (root.right == null && root.left == null) return root;
        TreeNode right = root.right, left = root.left;
        root.left = invertTree20211230(right);
        root.right = invertTree20211230(left);
        return root;
    }

}
