package pers.mihao.careerism.data_structures.dynamic_programming.斐波那契;

/**
 * 152. 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 *
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 */
public class Code152乘积最大子数组 {

    public static void main(String[] args) {
        System.out.println(new Code152乘积最大子数组().maxProduct20211211(new int[]{2,3,-2,4}));
        System.out.println(new Code152乘积最大子数组().maxProduct20211211(new int[]{-2,0,-1}));
    }

    /**
     * 穷举动态规划
     * @param nums
     * @return
     */
    public static int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (i == j) {
                    dp[j] = nums[i];
                }else {
                    dp[j] = dp[j-1] * nums[j];
                }
                max = Math.max(max, dp[j]);
            }
        }
        return max;
    }


    public int maxProduct20211211(int[] nums) {
        int length = nums.length;
        int[] maxF = new int[length];
        int[] minF = new int[length];
        System.arraycopy(nums, 0, maxF, 0, length);
        System.arraycopy(nums, 0, minF, 0, length);
        for (int i = 1; i < length; ++i) {
            maxF[i] = Math.max(maxF[i - 1] * nums[i], Math.max(nums[i], minF[i - 1] * nums[i]));
            minF[i] = Math.min(minF[i - 1] * nums[i], Math.min(nums[i], maxF[i - 1] * nums[i]));
        }
        int ans = maxF[0];
        for (int i = 1; i < length; ++i) {
            ans = Math.max(ans, maxF[i]);
        }
        return ans;
    }
}
