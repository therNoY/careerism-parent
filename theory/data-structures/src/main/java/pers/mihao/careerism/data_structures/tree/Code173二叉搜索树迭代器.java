package pers.mihao.careerism.data_structures.tree;

import java.util.Deque;
import java.util.LinkedList;
import pers.mihao.careerism.data_structures.util.TreeNode;

/**
 * 173. 二叉搜索树迭代器
 * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
 * 调用 next() 将返回二叉搜索树中的下一个最小的数。
 * 示例：
 * BSTIterator iterator = new BSTIterator(root);
 * iterator.next();    // 返回 3
 * iterator.next();    // 返回 7
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 9
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 15
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 20
 * iterator.hasNext(); // 返回 false
 * 提示：
 * next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。
 */
public class Code173二叉搜索树迭代器 {

    Deque<TreeNode> deque = new LinkedList<>();

    class BSTIterator {

        public BSTIterator(TreeNode root) {
            if (root == null) return;
            Deque<TreeNode> stack = new LinkedList<>();
            stack.add(root);
            while (stack.size() > 0) {
                TreeNode treeNode = stack.pollLast();
                if (treeNode == null) {
                    deque.add(stack.pollLast());
                } else {
                    if (treeNode.right != null) stack.addLast(treeNode.right);
                    stack.addLast(treeNode);
                    stack.add(null);
                    if (treeNode.left != null) stack.addLast(treeNode.left);
                }
            }
        }

        public int next() {
            return deque.pollFirst().val;
        }

        public boolean hasNext() {
            return deque.size() > 0;
        }
    }

    class BSTIterator20211228 {

        Deque<TreeNode> treeNodes = new LinkedList<>();

        public BSTIterator20211228(TreeNode root) {
            Deque<TreeNode> deque = new LinkedList<>();
            while (root != null || deque.size() > 0) {
                while (root != null) {
                    deque.addFirst(root);
                    root = root.left;
                }
                root = deque.pollFirst();
                treeNodes.addLast(root);
                root = root.right;
            }
        }

        public int next() {
            return treeNodes.pollFirst().val;
        }

        public boolean hasNext() {
            return !treeNodes.isEmpty();
        }
    }

}
