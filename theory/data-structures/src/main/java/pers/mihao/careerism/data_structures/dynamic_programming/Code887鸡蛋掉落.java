package pers.mihao.careerism.data_structures.dynamic_programming;

import java.util.Arrays;

/**
 * 887. 鸡蛋掉落
 * 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
 * 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
 * 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
 * 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
 * 你的目标是确切地知道 F 的值是多少。
 * 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
 * <p>
 * 示例 1：
 * <p>
 * 输入：K = 1, N = 2
 * 输出：2
 * 解释：
 * 鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
 * 否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
 * 如果它没碎，那么我们肯定知道 F = 2 。
 * 因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。
 * 示例 2：
 * <p>
 * 输入：K = 2, N = 6
 * 输出：3
 * 示例 3：
 * <p>
 * 输入：K = 3, N = 14
 * 输出：4
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= K <= 100
 * 1 <= N <= 10000
 */
public class Code887鸡蛋掉落 {
    public static void main(String[] args) {
        System.out.println(new Code887鸡蛋掉落().superEggDrop3(3, 14));
    }

    public int superEggDrop(int K, int N) {
        int[][] dp = new int[K][N];
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0) {
                    dp[0][j] = j;
                    continue;
                }
                if (j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                int min = N, temp;
                for (int k = 1; k < j; k++) {
                    temp = Math.max(dp[i - 1][k - 1], dp[i][j - k]) + 1;
                    min = min > temp ? temp : min;
                }
                dp[i][j] = min;
            }
        }
        return dp[K - 1][N - 1];
    }

    public int superEggDrop3(int K, int N) {
        /**
         * dp[i][j] 表示还剩i个鸡蛋和j层楼需要确定的最小鸡蛋数量
         */
        int[][] dp = new int[K + 1][N + 1];
        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == 1) {
                    dp[i][j] = j;
                } else if (j == 1) {
                    dp[i][j] = 1;
                } else {
                    /**
                     * 不知道在哪一层丢 就一层一层试一试
                     */
//                    int res = Integer.MAX_VALUE;
//                    for (int k = 1; k < j; k++) {
//                        res = Math.min(Math.max(dp[i - 1][k - 1], dp[i][j - k]) + 1, res);
//                    }
//                    dp[i][j] = res;
                    // 使用二分来试
                    int left = 1, right = j;
                    while (left < right){
                        int mid = left + (right - left + 1) / 2;
                        if (dp[i-1][mid-1] > dp[i][j-mid]) {
                            right = mid-1;
                        }else {
                            left = mid;
                        }
                    }
                    dp[i][j] = Math.max(dp[i - 1][left - 1], dp[i][j - left]) + 1;
                }

            }
        }
        return dp[K][N];
    }

    public int superEggDrop2(int K, int N) {
        // dp[i][j]：一共有 i 层楼梯的情况下，使用 j 个鸡蛋的最少实验的次数
        // 注意：
        // 1、i 表示的是楼层的大小，不是第几层的意思，例如楼层区间 [8, 9, 10] 的大小为 3，这一点是在状态转移的过程中调整的定义
        // 2、j 表示可以使用的鸡蛋的个数，它是约束条件，我个人习惯放在后面的维度，表示消除后效性的意思
        // 0 个楼层和 0 个鸡蛋的情况都需要算上去，虽然没有实际的意义，但是作为递推的起点，被其它状态值所参考
        int[][] dp = new int[N + 1][K + 1];
        // 由于求的是最小值，因此初始化的时候赋值为一个较大的数，9999 或者 i 都可以
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], i);
        }
        // 初始化：填写下标为 0、1 的行和下标为 0、1 的列
        // 第 0 行：楼层为 0 的时候，不管鸡蛋个数多少，都测试不出鸡蛋的 F 值，故全为 0
        for (int j = 0; j <= K; j++) {
            dp[0][j] = 0;
        }
        // 第 1 行：楼层为 1 的时候，0 个鸡蛋的时候，扔 0 次，1 个以及 1 个鸡蛋以上只需要扔 1 次
        dp[1][0] = 0;
        for (int j = 1; j <= K; j++) {
            dp[1][j] = 1;
        }
        // 第 0 列：鸡蛋个数为 0 的时候，不管楼层为多少，也测试不出鸡蛋的 F 值，故全为 0
        // 第 1 列：鸡蛋个数为 1 的时候，这是一种极端情况，要试出 F 值，最少次数就等于楼层高度（想想复杂度的定义）
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 0;
            dp[i][1] = i;
        }
        // 从第 2 行，第 2 列开始填表
        for (int i = 2; i <= N; i++) {
            for (int j = 2; j <= K; j++) {
                for (int k = 1; k <= i; k++) {
                    // 碎了，就需要往低层继续扔：层数少 1 ，鸡蛋也少 1
                    // 不碎，就需要往高层继续扔：层数是当前层到最高层的距离差，鸡蛋数量不少
                    // 两种情况都做了一次尝试，所以加 1
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k - 1][j - 1], dp[i - k][j]) + 1);
                }
            }
        }
        return dp[N][K];
    }
}

