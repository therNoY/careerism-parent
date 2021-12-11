package pers.mihao.careerism.data_structures.dynamic_programming.string;

/**
 * 978. 最长湍流子数组
 * 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
 * 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
 * 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
 * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
 * 返回 A 的最大湍流子数组的长度。
 * 示例 1：
 * 输入：[9,4,2,10,7,8,8,1,9]
 * 输出：5
 * 解释：(A[1] > A[2] < A[3] > A[4] < A[5])
 * 示例 2：
 * 输入：[4,8,12,16]
 * 输出：2
 * 示例 3：
 * 输入：[100]
 * 输出：1
 */
public class Code978最长湍流子数组 {

    public static void main(String[] args) {
        System.out.println(new Code978最长湍流子数组().maxTurbulenceSize20121211_(new int[]{2,0,2,4,2,5,0,1,2,3}));
    }

    public int maxTurbulenceSize(int[] A) {
        if (A.length == 1) {
            return 1;
        }
        int[] dp = new int[A.length];
        for (int i = 1; i < A.length; i++) {
            //如果i和i-1的值相等，那么i位的初始值为1，譬如A={9,9}，它返回的长度为1，而不是2。
            dp[i] = A[i] == A[i - 1] ? 1 : 2;
        }
        //初始化dp以后，从2到N去计算最长长度。
        //状态转移方程: dp[i] = dp[i-1] + 1;
        //i位的可能最大长度可能是：i-1位上最大长度 + 1（包含i自己）
        //那么什么时候可以加上自己算总长度呢，当i位和i-1位的大小正好跟i-1和i-2的大小情况相反。说明i成功可以加入到前面已经计算的总长度里。
        //否则i位就是默认初始化的长度。
        int max = dp[1];
        for (int i = 2; i < A.length; i++) {
            if (A[i - 1] - A[i - 2] > 0 && A[i] - A[i - 1] < 0 || A[i - 1] - A[i - 2] < 0 && A[i] - A[i - 1] > 0) {
                dp[i] = dp[i - 1] + 1;
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }


    /**
     *
     * @param arr
     * @return
     */
    public int maxTurbulenceSize20121211(int[] arr) {
        if (arr.length == 1) return 1;
        if (arr.length == 2) return arr[0] == arr[1] ? 1 : 2;
        /**
         * dp[j][0]表示0~j是偶数大的湍流数组
         * dp[j][1]表示0~j是基数大的湍流数组
         */
        boolean[][][] dp = new boolean[arr.length][arr.length][2];
        int max = 0;
        dp[0][0][0] = dp[0][0][1] = true;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (i == j){
                    dp[i][j][0] = true;
                    dp[i][j][1] = true;
                    max = Math.max(max, 1);
                } else {
                    dp[i][j][0] = dp[i][j-1][0] && ((j - i) % 2 == 0 ? arr[j] < arr[j - 1] : arr[j] > arr[j - 1]);
                    dp[i][j][1] = dp[i][j-1][1] && ((j - i) % 2 == 0 ? arr[j] > arr[j - 1] : arr[j] < arr[j - 1]);
                    max = (dp[i][j][0] || dp[i][j][1]) ? Math.max(j - i + 1, max) : max;
                }
            }
        }
        return max;
    }


    /**
     * 超出时间限制
     * @param arr
     * @return
     */
    public int maxTurbulenceSize20121211_(int[] arr) {
        if (arr.length == 1) return 1;
        if (arr.length == 2) return arr[0] == arr[1] ? 1 : 2;
        /**
         * dp[j][0]表示0~j是偶数大的湍流数组
         * dp[j][1]表示0~j是基数大的湍流数组
         */
        boolean[][] dp = new boolean[arr.length][2];
        boolean[][] dp2 = new boolean[arr.length][2];
        int max = 0;
        dp[0][0] = dp[0][1] = true;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (i == j){
                    dp[j][0] = true;
                    dp[j][1] = true;
                    max = Math.max(max, 1);
                } else {
                    dp[j][0] = dp2[j-1][0] && ((j - i) % 2 == 0 ? arr[j] < arr[j - 1] : arr[j] > arr[j - 1]);
                    dp[j][1] = dp2[j-1][1] && ((j - i) % 2 == 0 ? arr[j] > arr[j - 1] : arr[j] < arr[j - 1]);
                    max = (dp[j][0] || dp[j][1]) ? Math.max(j - i + 1, max) : max;
                }
                dp2[j][0] = dp[j][0];
                dp2[j][1] = dp[j][1];
            }
        }
        return max;
    }

}
