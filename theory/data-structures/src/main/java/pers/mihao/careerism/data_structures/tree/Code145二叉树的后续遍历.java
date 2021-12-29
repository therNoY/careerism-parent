package pers.mihao.careerism.data_structures.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import pers.mihao.careerism.data_structures.util.TreeNode;

/**
 * 145. 二叉树的后序遍历
 * 给定一个二叉树，返回它的 后序 遍历。
 * 示例:
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class Code145二叉树的后续遍历 {

    public List<Integer> postorderTraversal20211228(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        deque.addLast(root);
        while (deque.size() > 0) {
            TreeNode tail = deque.pollLast();
            if (tail != null) {
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
