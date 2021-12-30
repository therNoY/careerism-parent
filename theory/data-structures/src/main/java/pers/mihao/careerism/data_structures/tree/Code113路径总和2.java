package pers.mihao.careerism.data_structures.tree;

import pers.mihao.careerism.data_structures.mytree.Tree;
import pers.mihao.careerism.data_structures.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 113. 路径总和 II
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有
 * 从根节点到叶子节点 路径总和等于给定目标和的路径。
 *
 * 叶子节点 是指没有子节点的节点。
 * @version 1.0
 * @auther mihao
 * @date 2021\12\30 0030 20:28
 */
public class Code113路径总和2 {


    public static void main(String[] args) {
        System.out.println(new Code113路径总和2().
                helper(TreeNode.getTreeByArray(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1), 22));
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> lists = new ArrayList<>();
        getPathSum(root, sum, new LinkedList<>(), lists);
        return lists;
    }

    public void getPathSum(TreeNode node, int sum, LinkedList<Integer> history, List<List<Integer>> list) {
        if (node == null) return;
        history.addLast(node.val);
        if (node.right == null && node.left == null) {
            if (node.val == sum) list.add((List) history.clone());
        }
        getPathSum(node.left, sum - node.val, history, list);
        getPathSum(node.right, sum - node.val, history, list);
        history.removeLast();
    }

    public List<List<Integer>> pathSum20211230(TreeNode root, int targetSum) {
        return helper(root, targetSum);
    }

    private List<List<Integer>> helper(TreeNode root, int targetSum) {
        if (root == null) return new ArrayList<>();
        if (root.right == null && root.left == null && root.val == targetSum) {
            List<Integer> list = new LinkedList<>();
            ((LinkedList) list).addFirst(root.val);
            List<List<Integer>> res = new ArrayList<>();
            res.add(list);
            return res;
        }
        List<List<Integer>> left = helper(root.left, targetSum - root.val);
        List<List<Integer>> right = helper(root.right, targetSum - root.val);
        for (List<Integer> list : left) {
            ((LinkedList) list).addFirst(root.val);
        }
        for (List<Integer> list : right) {
            ((LinkedList) list).addFirst(root.val);
        }
        left.addAll(right);
        return left;
    }

}
