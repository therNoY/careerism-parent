package pers.mihao.careerism.data_structures.dynamic_programming;

/**
 * 96. 不同的二叉搜索树
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 */
public class Code96不同的二叉搜索树 {

    public static void main(String[] args) {
        System.out.println(numTrees(3));
        System.out.println(numTrees(4));
        System.out.println(new Code96不同的二叉搜索树().numTrees20211208(3));
        System.out.println(new Code96不同的二叉搜索树().numTrees20211208(4));

    }

    public static int numTrees(int n) {
        if (n < 2) return n;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[0] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                int a = dp[j - 1];
                int b = dp[i - j];
                dp[i] += a * b;
            }
        }
        return dp[n];
    }


    /**
     * 明确定义 左边的一定小 右边的一定大
     * @param n
     * @return
     */
    public int numTrees20211208(int n) {
        if (n <= 2) return n;
        /**
         * dp[i] 表示数量为i的二叉搜索树最大值(数字都是不一样的 所以最大值 只取决于数量)
         */
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }
}
