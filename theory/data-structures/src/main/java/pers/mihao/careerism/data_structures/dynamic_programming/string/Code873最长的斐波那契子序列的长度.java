package pers.mihao.careerism.data_structures.dynamic_programming.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 873. 最长的斐波那契子序列的长度 如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
 * <p>
 * n >= 3 对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2} 给定一个严格递增的正整数数组形成序列，找到 A 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。
 * （回想一下，子序列是从原序列 A 中派生出来的，它从 A 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列） 示例 1： 输入:
 * [1,2,3,4,5,6,7,8] 输出: 5 解释: 最长的斐波那契式子序列为：[1,2,3,5,8] 。 示例 2：
 * <p>
 * 输入: [1,3,7,11,12,14,18] 输出: 3 解释: 最长的斐波那契式子序列有： [1,11,12]，[3,11,14] 以及 [7,11,18] 。
 * @see Code300最长上升子序列 (二维的最长上升子序列)
 */
public class Code873最长的斐波那契子序列的长度 {

    public static void main(String[] args) {
        System.out.println(new Code873最长的斐波那契子序列的长度().lenLongestFibSubseq(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
    }

    /**
     * 将斐波那契式的子序列中的两个连续项 A[i], A[j] 视为单个结点 (i, j)，整个子序列是这些连续结点之间的路径。
     * 例如，对于斐波那契式的子序列 (A[1] = 2, A[2] = 3, A[4] = 5, A[7] = 8, A[10] = 13)，
     * 结点之间的路径为 (1, 2) <-> (2, 4) <-> (4, 7) <-> (7, 10)。
     * 这样做的动机是，只有当 A[i] + A[j] == A[k] 时，两结点 (i, j) 和 (j, k) 才是连通的，我们需要这些信息才能知道这一连通。
     * 现在我们得到一个类似于 最长上升子序列 的问题。
     *
     * 设 longest[i, j] 是结束在 [i, j] 的最长路径。那么 如果 (i, j) 和 (j, k) 是连通的，
     * longest[j, k] = longest[i, j] + 1。
     * 由于 i 由 A.index(A[k] - A[j]) 唯一确定，所以这是有效的：我们在 i 潜在时检查每组 j < k，
     * 并相应地更新 longest[j, k]。
     * @param arr
     * @return
     */
    private int lenLongestFibSubseq(int[] arr) {
        int N = arr.length;
        // 加快访问
        Map<Integer, Integer> index = new HashMap<>(N);
        for (int i = 0; i < N; ++i) {
            index.put(arr[i], i);
        }
        int[][] dp = new int[N][N];
        int ans = 0;
        for (int k = 0; k < N; ++k) {
            for (int j = 0; j < k; ++j) {
                int i = index.getOrDefault(arr[k] - arr[j], -1);
                if (i >= 0 && i < j) {
                    int cand = (dp[i][j] == 0 ? 2 : dp[i][j]) + 1;
                    dp[j][k] = cand;
                    ans = Math.max(ans, cand);
                }
            }
        }
        return ans >= 3 ? ans : 0;
    }

    /**
     * 思路 定义DP 1. dp[i][j] 数组 从i到j的斐波那契子序列的长度 ×
     * 2. dp[i][j] 数组 从i到j的斐波那契子序列最大长度 ×
     *
     * @param arr
     * @return
     */
    public int lenLongestFibSubseq20211201(int[] arr) {
        int l = arr.length, max = 0;
        Map<Integer, Integer> indexMap = new HashMap<>(l);
        for (int i = 0; i < l; i++) {
            indexMap.put(arr[i], i);
        }
        /**
         * dp[i][j] 表示以arr[i][j]为斐波那契子序列后两位的子序列的最大值
         */
        int[][] dp = new int[l][l];
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < i; j++) {
                // 初始化最小值
                dp[j][i] = 2;
                Integer index = indexMap.get(arr[i] - arr[j]);
                // 存在并且在前面
                if (index != null && index < j){
                    dp[j][i] = Math.max(dp[index][j] + 1, dp[j][i]);
                    max = Math.max(max, dp[j][i]);
                }
            }
        }
        return max > 2 ? max : 0;
    }

}
