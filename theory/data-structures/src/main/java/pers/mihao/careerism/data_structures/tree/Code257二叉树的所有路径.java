package pers.mihao.careerism.data_structures.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import pers.mihao.careerism.data_structures.util.TreeNode;

/**
 * @Author mh32736
 * @Date 2021/12/29 16:29
 */
public class Code257二叉树的所有路径 {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> strings = new ArrayList<>();
        preOrder(root, strings, new LinkedList<>());
        return strings;
    }

    private void preOrder(TreeNode root, List<String> res, LinkedList<Integer> history) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            res.add(newPath(history, root.val));
            return;
        }
        history.addLast(root.val);
        preOrder(root.left, res, history);
        preOrder(root.right, res, history);
        history.pollLast();
    }

    private String newPath(LinkedList<Integer> history, int val) {
        StringBuffer stringBuffer = new StringBuffer();
        history.forEach(h->{
            stringBuffer.append(h).append("->");
        });
        stringBuffer.append(val);
        return stringBuffer.toString();
    }


    public List<String> binaryTreePaths20211228(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        if (root.right == null && root.left == null) {
            res.add(root.val + "");
            return res;
        }
        List<String> left = binaryTreePaths20211228(root.left);
        for (String s :left) {
            res.add(root.val + "->" + s);
        }
        List<String> right = binaryTreePaths20211228(root.right);
        for (String s :right) {
            res.add(root.val + "->" + s);
        }
        return res;
    }
}
