package pers.mihao.careerism.data_structures.dynamic_programming.博弈;

/**
 * 1025. 除数博弈
 * 爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
 * 最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：
 * 选出任一 x，满足 0 < x < N 且 N % x == 0 。
 * 用 N - x 替换黑板上的数字 N 。
 * 如果玩家无法执行这些操作，就会输掉游戏。
 * 只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 false。假设两个玩家都以最佳状态参与游戏。
 * 示例 1：
 * 输入：2
 * 输出：true
 * 解释：爱丽丝选择 1，鲍勃无法进行操作。
 * 示例 2：
 * 输入：3
 * 输出：false
 * 解释：爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。
 * https://leetcode-cn.com/problems/divisor-game/submissions/
 */
public class Code1025除数博弈 {

    public static void main(String[] args) {
        System.out.println(new Code1025除数博弈().divisorGame(4));
        System.out.println(new Code1025除数博弈().divisorGame(5));
        System.out.println(new Code1025除数博弈().divisorGame(6));
        System.out.println(new Code1025除数博弈().divisorGame20211206(4));
        System.out.println(new Code1025除数博弈().divisorGame20211206(5));
        System.out.println(new Code1025除数博弈().divisorGame20211206(6));
    }

    public boolean divisorGame(int N) {
        if (N == 1) return false;
        if (N == 2) return true;
        boolean[] dp = new boolean[N];
        dp[0] = false;
        dp[1] = true;
        for (int i = 2; i < N; i++) {
            for (int j = 1; j < i; j++) {
                if (!dp[j] && (i - j + 1) % 2 == 0) {
                    dp[i] = true;
                    break;
                }
                dp[i] = false;
            }
        }
        return dp[N - 1];
    }

    public boolean divisorGame20211206(int n) {
        /**
         * dp[i][0] 表示数为i时对方选我是否能赢
         * dp[i][1] 表示数字为i时我选我是否能赢
         */
        boolean[][] dp = new boolean[n + 1][2];
        if (n == 1) return false;
        dp[1][0] = true;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (i % j == 0 && dp[i - j][0]) {
                    dp[i][1] = true;
                    break;
                }
            }
            dp[i][0] = !dp[i][1];
        }

        return dp[n][1];
    }


}
