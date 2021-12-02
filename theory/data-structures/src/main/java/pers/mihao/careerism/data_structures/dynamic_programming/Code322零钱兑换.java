package pers.mihao.careerism.data_structures.dynamic_programming;

/**
 * 322. 零钱兑换
 * <p>
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * <p>
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：coins = [1, 2, 5], amount = 11 输出：3 解释：11 = 5 + 5 + 1 示例 2：
 * <p>
 * 输入：coins = [2], amount = 3 输出：-1 示例 3：
 * <p>
 * 输入：coins = [1], amount = 0 输出：0 示例 4：
 * <p>
 * 输入：coins = [1], amount = 1 输出：1 示例 5：
 * <p>
 * 输入：coins = [1], amount = 2 输出：2
 * @Author mh32736
 * @Date 2021/12/1 14:45
 * @see 经典01背包
 * @see Code416分割等和子集
 * @see Code518零钱兑换2
 */
public class Code322零钱兑换 {

    public static void main(String[] args) {
        System.out.println(new Code322零钱兑换().coinChange(new int[]{1, 2, 5}, 11));
        System.out.println(new Code322零钱兑换().coinChange20211202(new int[]{1, 2, 5}, 11));

        System.out.println(new Code322零钱兑换().coinChange(new int[]{2,5,10,1}, 27));
        System.out.println(new Code322零钱兑换().coinChange20211202(new int[]{2,5,10,1}, 27));
    }


    /**
     * 标准答案dp
     * dp[i] 表示凑齐面额为i 最少需要多少硬币
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int[] dpTable = new int[amount + 1];
        int temp;
        dpTable[0] = 0;
        for (int i = 1; i < dpTable.length; i++) {
            int min = Integer.MAX_VALUE, tempI;
            for (int j = 0; j < coins.length; j++) {
                if (i == coins[j]) {
                    min = 1;
                    break;
                } else if ((tempI = i - coins[j]) >= 0) {
                    if ((temp = dpTable[tempI]) != Integer.MAX_VALUE) {
                        min = Math.min(min, temp + 1);
                    }
                }
            }
            dpTable[i] = min;
        }
        return dpTable[amount] != Integer.MAX_VALUE ? dpTable[amount] : -1;
    }

    /**
     * 两次通过 使用o-1背包的模板
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange20211202(int[] coins, int amount) {
        /**
         * dp[i][j] 定义 前i种硬币 总和为amount 所需硬币的最小值
         *
         * dp[i][j] = min(dp[i][j-coins[i]] + 1, dp[i-1][j])
         */
        int[][] dp = new int[coins.length][amount + 1];

        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j <= amount ; j++) {
                dp[i][j] = Integer.MAX_VALUE - 1;
                if (i == 0) {
                    if (j % coins[i] == 0) {
                        dp[i][j] = j / coins[i];
                    }
                    continue;
                }
                if (coins[i] == j) {
                    dp[i][j] = 1;
                } else if (coins[i] < j) {
                    dp[i][j] = Math.min(dp[i][j - coins[i]] + 1, dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[coins.length - 1][amount] == Integer.MAX_VALUE - 1 ? -1 : dp[coins.length - 1][amount];
    }

}
