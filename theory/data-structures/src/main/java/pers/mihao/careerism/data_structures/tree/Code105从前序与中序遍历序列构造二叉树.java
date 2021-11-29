package pers.mihao.careerism.data_structures.tree;

import java.util.HashMap;
import java.util.Map;
import pers.mihao.careerism.data_structures.util.TreeNode;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * 注意:
 * 你可以假设树中没有重复的元素。
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * @Author mihao
 * @Date 2021/3/26 9:14
 */
public class Code105从前序与中序遍历序列构造二叉树 {

    public static void main(String[] args) {
        new Code105从前序与中序遍历序列构造二叉树().buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7}).print();
//        new Code105从前序与中序遍历序列构造二叉树().buildTree(new int[]{1,2,4,8,9,5,3,6,7}, new int[]{8,4,9,2,5,1,6,3,7}).print();
//         new Code105从前序与中序遍历序列构造二叉树().buildTree(new int[]{1,2}, new int[]{1,2}).print();
//        new Code105从前序与中序遍历序列构造二叉树().buildTree(new int[]{1, 2, 3, 4}, new int[]{4, 3, 2, 1}).print();
//         new Code105从前序与中序遍历序列构造二叉树().buildTree(new int[]{1,2,3,4}, new int[]{1,3,2,4}).print();
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inOrderMap = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            inOrderMap.put(inorder[i], i);
        }
        return buildTree(preorder, inOrderMap, 0, preorder.length - 1, 0);
    }


    public TreeNode buildTree(int[] preorder, Map<Integer, Integer> inOrderMap,  int s, int e, int rs) {
        if (s >= preorder.length || s < 0 || e < s) return null;
        TreeNode root = new TreeNode(preorder[s]);
        if (e == s) return root;
        int index = inOrderMap.get(preorder[s]);
        int leftNum = index - rs;
        root.left = buildTree(preorder, inOrderMap, s + 1, s + leftNum, rs);
        root.right = buildTree(preorder, inOrderMap, s + leftNum + 1, e, index + 1);
        return root;
    }
}
