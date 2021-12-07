package pers.mihao.careerism.data_structures.dynamic_programming.打家劫舍;

import java.util.Arrays;

/**
 * 213. 打家劫舍 II
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 * 示例 1：
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 示例 2：
 *
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：0
 * @Author mh32736
 * @Date 2021/12/5 21:01
 * @see Code377打家劫舍3
 */
public class Code213打家劫舍2 {

    public static void main(String[] args) {
        System.out.println(new Codes打家劫舍().rob2(new int[]{2, 3, 2}));
        System.out.println(new Codes打家劫舍().rob2(new int[]{1, 2, 3, 1}));
    }

    /**
     * 解体思路为要么偷1到最后 要么偷0到最后减一的最大值
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        return Math
            .max(doRob(Arrays.copyOfRange(nums, 1, nums.length)), doRob(Arrays.copyOfRange(nums, 0, nums.length - 1)));
    }

    public int doRob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int[][] dp = new int[nums.length][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i][1] = dp[i - 1][0] + nums[i];
            dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0]);
        }

        return Math.max(dp[nums.length - 1][1], dp[nums.length - 1][0]);

    }


    /**
     * 三围dp增加状态
     * @param nums
     * @return
     */
    public int rob20211205(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        /**
         * dp[i][1][1] => dp[i][0][1]
         */
        int[][][] dp = new int[nums.length][2][2];
        dp[0][1][1] = nums[0];
        dp[0][0][0] = 0;
        dp[0][1][0] = dp[0][0][1] = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            dp[i][0][0] = Math.max(dp[i - 1][0][0], dp[i -1][1][0]);
            dp[i][1][1] = dp[i - 1][0][1] + nums[i];
            dp[i][0][1] = Math.max(dp[i - 1][0][1], dp[i - 1][1][1]);
            dp[i][1][0] = dp[i - 1][0][0] + nums[i];
        }

        return getMax(dp[nums.length - 1][0][0], dp[nums.length - 1][0][1], dp[nums.length - 1][1][0]);
    }

    public int getMax(int... nums) {
        int max = -1;
        for (int n : nums) {
            if (n > max) max = n;
        }
        return max;
    }

}
