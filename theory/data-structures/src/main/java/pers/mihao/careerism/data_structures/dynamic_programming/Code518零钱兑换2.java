package pers.mihao.careerism.data_structures.dynamic_programming;

/**
 * 518. 零钱兑换 II
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
 * 示例 1:
 * 输入: amount = 5, coins = [1, 2, 5]
 * 输出: 4
 * 解释: 有四种方式可以凑成总金额:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * 示例 2:
 * 输入: amount = 3, coins = [2]
 * 输出: 0
 * 解释: 只用面额2的硬币不能凑成总金额3。
 * 示例 3:
 * 输入: amount = 10, coins = [10]
 * 输出: 1
 * 注意:
 * 你可以假设：
 * 0 <= amount (总金额) <= 5000
 * 1 <= coin (硬币面额) <= 5000
 * 硬币种类不超过 500 种
 * 结果符合 32 位符号整数
 * @see Code322零钱兑换
 */
public class Code518零钱兑换2 {

    public static void main(String[] args) {
        System.out.println(new Code518零钱兑换2().change(5, new int[]{1, 2, 5}));
        System.out.println(new Code518零钱兑换2().change20211202(5, new int[]{1, 2, 5}));
    }

    public int change(int amount, int[] coins) {
        // dp[i][j] 表示存放前i个硬币总金额是j的时候 最多的容量
        int[][] dp = new int[coins.length + 1][amount + 1];

        for (int i = 0; i <= coins.length; i++)
            dp[i][0] = 1;
        int temp;
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if ((temp = j - coins[i - 1]) >= 0) {
                    // 可以存的下 旧村
                    dp[i][j] = dp[i - 1][j] +  dp[i][temp];
                }else {
                    dp[i][j] = dp[i - 1][j];
                }
            }

        }
        return dp[coins.length][amount];
    }

    /**
     * 提交一把过
     * @param amount
     * @param coins
     * @return
     */
    public int change20211202(int amount, int[] coins) {
        /**
         * dp[i][j] 表示使用前i个硬币凑齐总金额为j的最多种类
         * dp[i][j] dp[i-1][j] + dp[i][j-coins[i]]]
         */
        int[][] dp = new int[coins.length][amount + 1];
        for (int i = 0; i < coins.length; i++) {
            for (int j = 0; j < amount + 1; j++) {
                if (j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                if (i == 0) {
                    if (j % coins[i] == 0) {
                        dp[i][j] = 1;
                    }
                    continue;
                }
                if (j >= coins[i]) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[coins.length - 1][amount];
    }

}
