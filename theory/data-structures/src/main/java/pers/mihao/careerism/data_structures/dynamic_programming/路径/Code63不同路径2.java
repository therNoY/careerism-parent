package pers.mihao.careerism.data_structures.dynamic_programming.路径;

/**
 *
 * Code 63 不同路径2
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * <p>
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * <p>
 * <p>
 * <p>
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * <p>
 * 说明：m 和 n 的值均不超过 100。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * 输出: 2
 * 解释:
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code63不同路径2 {

    public static void main(String[] args) {
        System.out.println(new Code63不同路径2().uniquePathsWithObstacles(new int[][]{{0,0, 0},{0,1,0},{0,0,0}}));
        System.out.println(new Code63不同路径2().uniquePathsWithObstacles20211207(new int[][]{{0,0, 0},{0,1,0},{0,0,0}}));
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid[0].length;
        int m = obstacleGrid.length;

        if (m == 0) return n;
        if (n == 0) return m;

        int[][] dp = new int[m][n];
        dp[0][0] = 0;

        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 0) {
                dp[0][i] = 1;
            } else {
                break;
            }
        }

        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 0) {
                dp[i][0] = 1;
            } else {
                break;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) dp[i][j] = 0;
                else dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 用到了两个位运算 节省了代码
     * 1 ^ 1 = 0
     * 0 ^ 1 = 1
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles20211207(int[][] obstacleGrid) {
        int m  = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int bit = obstacleGrid[i][j] ^ 1;
                if (i == 0 && j == 0) {
                    dp[i][j] = bit;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] & bit;
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] & bit;
                } else {
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) * bit;
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
