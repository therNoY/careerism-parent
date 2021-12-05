package pers.mihao.careerism.data_structures.dynamic_programming;

/**
 * 474.一和零 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y
 * 的 子集 。 示例 1： 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3 输出：4 解释：最多有 5 个 0 和 3 个 1 的最大子集是
 * {"10","0001","1","0"} ，因此答案是 4 。 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3
 * 。 示例 2： 输入：strs = ["10", "0", "1"], m = 1, n = 1 输出：2 解释：最大的子集是 {"0", "1"} ，所以答案是 2 。 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ones-and-zeroes 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author hspcadmin
 * @see Code377组合总和4
 */
public class Code474一和零 {


    public int findMaxForm(String[] strs, int m, int n) {
        int length = strs.length;
        int[][][] dp = new int[length + 1][m + 1][n + 1];
        for (int i = 1; i <= length; i++) {
            int[] zerosOnes = getZerosOnes(strs[i - 1]);
            int zeros = zerosOnes[0], ones = zerosOnes[1];
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j >= zeros && k >= ones) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - zeros][k - ones] + 1);
                    }
                }
            }
        }
        return dp[length][m][n];
    }

    public int[] getZerosOnes(String str) {
        int[] zerosOnes = new int[2];
        int length = str.length();
        for (int i = 0; i < length; i++) {
            zerosOnes[str.charAt(i) - '0']++;
        }
        return zerosOnes;
    }


    public int findMaxForm20211205(String[] strs, int m, int n) {
        return -1;
    }


    /**
     * 有nums数组 加起来和 小于m的最大子串的数量
     *
     * @param nums
     * @param m
     * @return
     */
    public int findMaxForm(int[] nums, int m) {
        /**
         * dp[i][j] 表示前i个数 和加起来小于m的最大子集的数量的信息数组
         */
        int[][][] dp = new int[nums.length][m + 1][2];

        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < m + 1; j++) {
                dp[i][j] = new int[2];
                if (dp[i - 1][j][0] + nums[i] <= m) {
                    dp[i][j][0] = dp[i - 1][j][0] + nums[i];
                }
            }
        }

        return dp[nums.length - 1][m][1];
    }


}
