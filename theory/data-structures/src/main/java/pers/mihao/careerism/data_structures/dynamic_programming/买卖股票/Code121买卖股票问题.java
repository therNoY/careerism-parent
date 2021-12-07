package pers.mihao.careerism.data_structures.dynamic_programming.买卖股票;

/**
 * 121. 买卖股票的最佳时机(只买卖一次)
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 * 示例 1：
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2：
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 * @Author mh32736
 * @Date 2021/12/5 13:43
 */
public class Code121买卖股票问题 {

    public static void main(String[] args) {
        Code121买卖股票问题 code = new Code121买卖股票问题();
        System.out.println(code.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(code.maxProfit20211205(new int[]{7, 1, 5, 3, 6, 4}));
    }

    public int maxProfit(int[] prices) {
        int maxPro = 0, temp, minPri = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPri) minPri = prices[i];
            if ((temp = prices[i] - minPri) > 0 && temp > maxPro) maxPro = temp;
        }
        return maxPro;
    }

    public int maxProfit20211205(int[] prices) {
        /**
         * dp[i][1] 表示我的上一天持有的最大
         * dp[i][0] 表示我的上一个没有持有股票的最大
         */
        int[][] dp = new int[prices.length][2];
        for (int i = 0; i < prices.length; i++) {
            if (i == 0) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], - prices[i]);
        }
        return Math.max(dp[prices.length - 1][1], dp[prices.length - 1][0]);
    }

}
