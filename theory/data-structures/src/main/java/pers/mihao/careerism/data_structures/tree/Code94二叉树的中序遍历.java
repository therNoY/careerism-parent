package pers.mihao.careerism.data_structures.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import pers.mihao.careerism.data_structures.util.TreeNode;

/**
 * @Author mihao
 * @Date 2021/6/24 10:36
 */
public class Code94二叉树的中序遍历 {

    public static void main(String[] args) {
        TreeNode treeNode = TreeNode.getTreeByArray(1, 2, 3, 4, 5, 6, 7, 8, 9);
        treeNode.print();
        List<Integer> res =  new Code94二叉树的中序遍历().inorderTraversal1(treeNode);
        System.out.println(res);
        List<Integer> res3 =  new Code94二叉树的中序遍历().inorderTraversal4(treeNode);
        System.out.println(res3);
    }

    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        inOrder(root, list);
        return list;
    }

    public void inOrder(TreeNode node, List<Integer> integerList) {
        if (node.left != null) inOrder(node.left, integerList);
        integerList.add(node.val);
        if (node.right != null) inOrder(node.right, integerList);
    }


    /**
     * 真正的进栈出栈顺序一样
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        TreeNode first;
        while (deque.size() > 0) {
            first = deque.getLast();
            if (first.left != null && !first.left.isVisit) {
                deque.addLast(first.left);
            } else {
                list.add(first.val);
                if (first.right != null && !first.right.isVisit) {
                    deque.addLast(first.right);
                } else {
                    deque.pollLast().isVisit = true;
                }
            }
        }
        return list;
    }

    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> deque = new LinkedList<>();
        TreeNode currNode = root;
        while (currNode != null || deque.size() > 0) {
            while (currNode != null) {
                deque.addLast(currNode);
                currNode = currNode.left;
            }
            currNode = deque.pollLast();
            res.add(currNode.val);
            currNode = currNode.right;
        }
        return res;
    }


    public List<Integer> inorderTraversal4(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        TreeNode tail;
        while (deque.size() > 0) {
            tail = deque.pollLast();
            if (tail != null){
                if (tail.right != null) deque.addLast(tail.right);
                deque.addLast(tail);
                deque.addLast(null);
                if (tail.left != null) deque.addLast(tail.left);
            } else {
                res.add(deque.pollLast().val);
            }
        }
        return res;
    }


}
