package pers.mihao.careerism.data_structures.tree;

import java.util.Deque;
import java.util.LinkedList;
import pers.mihao.careerism.data_structures.util.TreeNode;

/**
 * 98.给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 假设一个二叉搜索树具有如下特征：
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 * 输入:
 * 2
 * / \
 * 1   3
 * 输出: true
 * 示例 2:
 * 输入:
 * 5
 * / \
 * 1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code98验证二叉搜索树 {

    public boolean isValidBST(TreeNode root) {
        Integer num = null;
        if (root == null) return true;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        while (deque.size() > 0) {
            TreeNode node = deque.pollLast();
            if (node != null) {
                if (node.right != null) deque.addLast(node.right);
                deque.add(node);
                deque.add(null);
                if (node.left != null) deque.addLast(node.left);
            } else {
                node = deque.pollLast();
                if (num == null || num < node.val) {
                    num = node.val;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidBST20211228(TreeNode root) {
        int lasNum = 0;
        boolean isFirst = true;
        Deque<TreeNode> deque = new LinkedList<>();
        while (root != null || !deque.isEmpty()) {
            while (root != null) {
                deque.addFirst(root);
                root = root.left;
            }
            root = deque.pollFirst();
            if (isFirst) {
                lasNum = root.val;
                isFirst = false;
            } else if (lasNum >= root.val) {
                return false;
            } else {
                lasNum = root.val;
            }
            root = root.right;
        }
        return true;
    }

}
