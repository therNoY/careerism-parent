package pers.mihao.careerism.data_structures.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import pers.mihao.careerism.data_structures.util.TreeNode;

/**
 * @Author mihao
 * @Date 2021/6/17 20:36
 */
public class Code144二叉树的前序遍历 {


    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.getTreeByArray(1, 2, 3, 4, 5, 6, 7, 8, 9);
        treeNode.print();
        List<Integer> res =  new Code144二叉树的前序遍历().preOrder3(treeNode);
        System.out.println(res);
        List<Integer> res2 =  new Code144二叉树的前序遍历().preOrder1(treeNode);
        System.out.println(res2);
    }


    /**
     * 使用递归栈完成
     * @param root
     * @return
     */
    public List<Integer> preOrder1(TreeNode root) {
        List<Integer> integers = new ArrayList<>();
        preOrder1(integers, root);
        return integers;
    }

    public void preOrder1(List<Integer> list, TreeNode node) {
        if (node == null) return;
        list.add(node.val);
        preOrder1(list, node.left);
        preOrder1(list, node.right);
    }

    /**
     * 使用模拟递归栈
     * @param root
     * @return
     */
    public List<Integer> preOrder2(TreeNode root) {
        List<Integer> integers = new ArrayList<>();
        if (root == null) return integers;
        Deque<TreeNode> nodes = new LinkedList<>();
        nodes.push(root);
        TreeNode first;
        while (nodes.size() > 0) {
            first = nodes.pollFirst();
            integers.add(first.val);
            if (first.right != null) nodes.push(first.right);
            if (first.left != null) nodes.push(first.left);
        }
        return integers;
    }

    /**
     * 使用模拟递归栈
     * @param root
     * @return
     */
    public List<Integer> preOrder3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        while (deque.size() > 0) {
            TreeNode node = deque.pollLast();
            if (node != null) {
                if (node.right != null) deque.addLast(node.right);
                if (node.left != null) deque.addLast(node.left);
                deque.addLast(node);
                deque.addLast(null);
            } else {
                node = deque.removeLast();
                res.add(node.val);
            }
        }
        return res;
    }

}
