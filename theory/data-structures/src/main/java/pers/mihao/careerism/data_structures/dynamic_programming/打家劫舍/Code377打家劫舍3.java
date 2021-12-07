package pers.mihao.careerism.data_structures.dynamic_programming.打家劫舍;

import pers.mihao.careerism.data_structures.util.TreeNode;

/**
 * 337. 打家劫舍 III
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，
 * 每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 * 示例 1:
 * 输入: [3,2,3,null,3,null,1]
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * 示例 2:
 *
 * 输入: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 * @Author mh32736
 * @Date 2021/12/6 9:30
 */
public class Code377打家劫舍3 {

    public static void main(String[] args) {

    }

    public int rob(TreeNode root) {
        int[] res = bestRob(root);
        return Math.max(res[0], res[1]);

    }

    public int[] bestRob(TreeNode node) {
        int[] res = new int[2];
        if (node == null) return res;
        int[] leftRob = bestRob(node.left);
        int[] rightRob = bestRob(node.right);
        res[0] = Math.max(leftRob[1], leftRob[0]) + Math.max(rightRob[1], rightRob[0]);
        res[1] = leftRob[0] + rightRob[0] + node.val;
        return res;
    }


    public int rob20211206(TreeNode root) {
        int[] rob = doRob(root);
        return Math.max(rob[0], rob[1]);
    }

    /**
     * 返回当前节点偷的最大值和不偷的最大值
     * @param node
     * @return
     */
    public int[] doRob(TreeNode node){
        int[] rob = new int[2];
        if (node == null) return rob;
        int[] robR = doRob(node.right);
        int[] robL = doRob(node.left);
        rob[0] = Math.max(robR[0], robR[1]) + Math.max(robL[0], robL[1]);
        rob[1] = robR[0] + robL[0] + node.val;
        return rob;
    }

}
