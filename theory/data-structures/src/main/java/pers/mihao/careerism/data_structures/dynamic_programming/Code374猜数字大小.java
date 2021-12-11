package pers.mihao.careerism.data_structures.dynamic_programming;

/**
 * 374. 猜数字大小
 * 猜数字游戏的规则如下：
 * 每轮游戏，我都会从 1 到 n 随机选择一个数字。 请你猜选出的是哪个数字。
 * 如果你猜错了，我会告诉你，你猜测的数字比我选出的数字是大了还是小了。
 * 你可以通过调用一个预先定义好的接口 int guess(int num) 来获取猜测结果，返回值一共有 3 种可能的情况（-1，1 或 0）：
 * -1：我选出的数字比你猜的数字小 pick < num
 * 1：我选出的数字比你猜的数字大 pick > num
 * 0：我选出的数字和你猜的数字一样。恭喜！你猜对了！pick == num
 * 返回我选出的数字。
 * 示例 1：
 * 输入：n = 10, pick = 6
 * 输出：6
 * 示例 2：
 * 输入：n = 1, pick = 1
 * 输出：1
 * 示例 3：
 * 输入：n = 2, pick = 1
 * 输出：1
 * 示例 4：
 * 输入：n = 2, pick = 2
 * 输出：2
 */
public class Code374猜数字大小 {

    int guess = Integer.MAX_VALUE;

    public static void main(String[] args) {
        System.out.println(new Code374猜数字大小().guessNumber(Integer.MAX_VALUE));
//        System.out.println(new Code374猜数字大小().getMoneyAmount2(10));
    }

    public int guessNumber(int n) {
        long guessNum, guessRes, start = 0, end = n;
        while ((guessRes = guess((int) (guessNum = (end - start + 1) / 2 + start))) != 0) {
            if (guessRes < 0) end = guessNum;
            else start = guessNum;
        }
        return (int) guessNum;
    }

    int guess(int pick) {
        return Integer.compare(guess, pick);
    }

    /**
     * 我们正在玩一个猜数游戏，游戏规则如下：
     * 我从 1 到 n 之间选择一个数字，你来猜我选了哪个数字。
     * 每次你猜错了，我都会告诉你，我选的数字比你的大了或者小了。
     * 然而，当你猜了数字 x 并且猜错了的时候，你需要支付金额为 x 的现金。直到你猜到我选的数字，你才算赢得了这个游戏。
     * 示例:
     * n = 10, 我选择了8.
     * 第一轮: 你猜我选择的数字是5，我会告诉你，我的数字更大一些，然后你需要支付5块。
     * 第二轮: 你猜是7，我告诉你，我的数字更大一些，你支付7块。
     * 第三轮: 你猜是9，我告诉你，我的数字更小一些，你支付9块。
     * 游戏结束。8 就是我选的数字。
     * 你最终要支付 5 + 7 + 9 = 21 块钱。
     * 给定 n ≥ 1，计算你至少需要拥有多少现金才能确保你能赢得这个游戏。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public int getMoneyAmount2(int n) {
        int[][] dp = new int[n + 2][n + 2];
        for (int i = n; i > 0; i--) {
            for(int j = i; j <= n; ++j) {
                if (i == j) {
                    dp[i][i] = 0;
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int k = i; k <= j; k++) {
                        min = Math.min(Math.max(dp[i][k - 1], dp[k + 1][j]) + k, min);
                    }
                    dp[i][j] = min;
                }

            }
        }
        return dp[1][n];
    }

}
