package pers.mihao.careerism.data_structures.dynamic_programming.买卖股票;

/**
 * 123. 买卖股票的最佳时机 III
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *
 * @Author mh32736
 * @Date 2021/12/5 14:42
 */
public class Code123买卖股票的最佳时机3 {

    public static void main(String[] args) {
        System.out.println(new Code123买卖股票的最佳时机3().maxProfit(new int[]{2, 4, 1}));
        System.out.println(new Code123买卖股票的最佳时机3().maxProfit20211205(new int[]{2, 4, 1}));
    }

    public int maxProfit(int[] prices) {
        if (prices.length <= 1) return 0;
        int k = 2, max = Integer.MIN_VALUE / 2;
        int[][][] dp = new int[prices.length][k + 1][2];
        dp[0][0][0] = 0;
        dp[0][1][1] = -prices[0];
        dp[0][0][1] = max;
        dp[0][1][0] = max;
        for (int i = 2; i <= k; i++) {
            dp[0][i][0] = max;
            dp[0][i][1] = max;
        }
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j - 1][0] - prices[i], dp[i - 1][j][1]);
                max = max < dp[i][j][0] ? dp[i][j][0] : max;
            }
        }
        return max > 0 ? max : 0;


    }

    public int maxProfit20211205(int[] prices) {
        /**
         * dp[i][1][j] 表示第i天的第j笔交易持有的最大
         * dp[i][0][j] 表示我的上一个没有持有股票的最大
         */
        int k = 2, max = Integer.MIN_VALUE / 2;
        int[][][] dp = new int[prices.length][2][k + 1];
        dp[0][0][0] = 0;
        dp[0][1][0] = max;
        dp[0][1][1] = -prices[0];
        dp[0][0][1] = max;
        for (int j = 1; j <= k; j++) {
            dp[0][1][k] = dp[0][0][k] = max;
        }
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][0][j] = Math.max(dp[i - 1][0][j], dp[i - 1][1][j] + prices[i]);
                dp[i][1][j] = Math.max(dp[i - 1][1][j], dp[i - 1][0][j - 1] - prices[i]);
                max = Math.max(Math.max(dp[i][0][j], dp[i][1][j]), max);
            }
        }
        return max > 0 ? max : 0;
    }

}
