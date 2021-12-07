package pers.mihao.careerism.data_structures.dynamic_programming.路径;

/**
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 * <p>
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code70爬楼梯 {

    public static void main(String[] args) {

        System.out.println(new Code70爬楼梯().climbStairs(3));
        System.out.println(new Code70爬楼梯().climbStairs20211206(3));

    }

    public int climbStairs(int n) {
        if (n < 3) return n;
        int s1 = 1, s2 = 2, s = 0;
        for (int i = 2; i < n; i++) {
            s = s1 + s2;
            s1 = s2;
            s2 = s;
        }
        return s;
    }


    public int climbStairs20211206(int n) {
        /**
         * dp[i]表示前i台阶需要多少种方法
         */
        int[] dp = new int[n + 1];
        if (n <= 2) return n;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
