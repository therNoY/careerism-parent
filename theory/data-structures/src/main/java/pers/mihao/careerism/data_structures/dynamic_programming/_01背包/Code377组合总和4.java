package pers.mihao.careerism.data_structures.dynamic_programming._01背包;

/**
 * 377. 组合总和 Ⅳ
 * 给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。
 * <p>
 * 示例:
 * <p>
 * nums = [1, 2, 3]
 * target = 4
 * <p>
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * <p>
 * 请注意，顺序不同的序列被视作不同的组合。
 * <p>
 * 因此输出为 7。
 */
public class Code377组合总和4 {

    public static void main(String[] args) {
        System.out.println(new Code377组合总和4().combinationSum4(new int[]{
            2, 1, 3
        }, 4));
        System.out.println(new Code377组合总和4().combinationSum420211202(new int[]{
            2, 1, 3
        }, 4));
    }


    public int combinationSum4(int[] nums, int target) {
//        count = 0;
//        backtrack(nums, new ArrayList<>(), target);
//        return count;
        /**
         * dp[i] 表示 目标数是j的前i个数组合总数量
         */
        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int i = 1; i < target+1; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i >= nums[j]) dp[i] += dp[i - nums[j]];
            }
        }
        return dp[target];
    }


    /**
     * dp[i] 使用前i个数组合大小为target的最大组合次数
     * dp[i] = Σ dp[i-1]
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum420211202(int[] nums, int target) {
        /**
         * dp[i]：和为 i 的正整数的组合个数。
         */
        int[] dp = new int[target + 1];
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] < i) {
                    dp[i] += dp[i - nums[j]];
                } else if (nums[j] == i) {
                    dp[i] += 1;
                }
            }
        }
        return dp[target];
    }

}
