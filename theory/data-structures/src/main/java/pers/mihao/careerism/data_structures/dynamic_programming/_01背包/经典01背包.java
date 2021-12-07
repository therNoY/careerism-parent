package pers.mihao.careerism.data_structures.dynamic_programming._01背包;

/**
 * 假定有一个容量固定背包 有若干个重量和价值不相等的物品
 * 给出一个策略 使得装进背包的物品价值最大话
 *
 * 0-1背包问题
 * @see Code416分割等和子集
 * @see Code322零钱兑换
 * @author hspcadmin
 */
public class 经典01背包 {

    public static void main(String[] args) {
//        int[] w = new int[]{35, 30, 60, 50, 40, 10, 25};
//        int[] p = new int[]{10, 40, 30, 50, 35, 40, 30};
//        int c = 150;

//        int[] w = new int[]{2, 2, 6, 5, 4};
//        int[] p = new int[]{6, 3, 5, 4, 6};
//        int c = 10;

        int[] w = new int[]{0,2,2,6,5,4};
        int[] p = new int[]{0,6,3,5,4,6};
        int c = 10;

        System.out.println(getMaxP2(w, p, c));
//        System.out.println(new 经典01背包()._01Bages(w, p, c));
        System.out.println(new 经典01背包()._01Bages2(w, p, c));
        System.out.println(new 经典01背包()._01Bages20211202(w, p, c));
    }

    /**
     * 这个也可以压缩成两个一维数组
     *
     * @param w
     * @param p
     * @param c
     * @return
     */
    static int getMaxP2(int[] w, int[] p, int c) {
        /**
         * 定义dp[i][j] 是前i个物品 j个重量的时候最大价值
         */
        int[][] dp = new int[w.length + 1][c + 1];
        for (int i = 1; i <= w.length; i++) {
            for (int j = 1; j <= c; j++) {
                // 如果装得下
                if (j >= w[i - 1]) {
                    // 选择装或者不装 不装和之前一样 装的话选择
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i - 1]] + p[i - 1]);
                } else {
                    // 只能不装
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[w.length][c];
    }

    /**
     * 01背包
     *
     * @param w
     * @param p
     * @param c
     * @return
     */
    public int _01Bages(int[] w, int[] p, int c) {
        int size = w.length;
        /**
         * 前i个物品装进背包容量是j的最大值
         */
        int[][] dp = new int[size + 1][c + 1];
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= c; j++) {
                if (j - w[i] > 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i - 1]] + p[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[size][c];
    }

    /**
     *
     * 经典01背包 装了之后不能再装
     *
     * @param w 重量数组
     * @param p 价值数组
     * @param c 价值总量
     * @return 最大价值
     */
    public int _01Bages2(int[] w, int[] p, int c) {

        /**
         * dp[i][j] 表示前i个装进总量是j的包最大价值
         */
        int[][] dp = new int[w.length][c + 1];

        for (int i = 0; i < w.length; i++) {
            for (int j = 1; j <= c; j++) {
                if (i > 0) {
                    if (w[i] > j) {
                        dp[i][j] = dp[i - 1][j];
                    }else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i]] + p[i]);
                    }
                }else if (w[i] <= j) {
                    dp[i][j] = p[i];
                }

            }
        }
        return dp[w.length - 1][c];
    }


    /**
     *
     * bingo dp 思路正确
     * @param w 重量
     * @param p 价值
     * @param c 背包总量
     * @return
     */
    public int _01Bages20211202(int[] w, int[] p, int c) {
        /**
         * dp[i][j] 表示装前i个物品 背包容量是j的时候能装进去的最大价值
         * 状态转移方程为 最后一个物品装 或者不装的最大值
         * dp[i][j] = max(dp[i-1][j-w[i]] + p[i], dp[i-1][j])
         *
         */
        int[][] dp = new int[w.length][c + 1];

        for (int i = 0; i < w.length; i++) {
            for (int j = 1; j <= c; j++) {
                if (i == 0 && j >= w[i]) {
                    if (j >= w[i]) {
                        dp[i][j] = p[i];
                        continue;
                    }
                }
                if (j >= w[i]) {
                    // 能装下
                    dp[i][j] = Math.max(dp[i - 1][j - w[i]] + p[i], dp[i - 1][j]);
                } else {
                    // 装不下
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[w.length - 1][c];
    }


}
