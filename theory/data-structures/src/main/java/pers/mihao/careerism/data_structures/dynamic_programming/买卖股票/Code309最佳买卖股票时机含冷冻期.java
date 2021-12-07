package pers.mihao.careerism.data_structures.dynamic_programming.买卖股票;

/**
 * 309. 最佳买卖股票时机含冷冻期
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 示例:
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 * @Author mh32736
 * @Date 2021/12/5 17:01
 */
public class Code309最佳买卖股票时机含冷冻期 {

    public static void main(String[] args) {
        System.out.println(new Code309最佳买卖股票时机含冷冻期().maxProfit(new int[]{6,1,3,2,4,7}));
        System.out.println(new Code309最佳买卖股票时机含冷冻期().maxProfit20211205(new int[]{6,1,3,2,4,7}));
    }


    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            if (i == 1) {
                dp[i][1] = dp[i - 1][1];
            } else {
                dp[i][1] = Math.max(dp[i - 2][0] - prices[i], dp[i - 1][1]);
            }

        }
        return dp[prices.length - 1][0] > 0 ? dp[prices.length - 1][0] : 0;
    }


    public int maxProfit20211205(int[] prices) {
        if (prices.length <= 1) return 0;
        /**
         * dp[i][0] 表示前i个元素,最后一次是没持有最多的交易次数
         */
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], (i >= 2 ? dp[i - 2][0] : 0) - prices[i]);
        }
        return dp[prices.length - 1][0];
    }

}
