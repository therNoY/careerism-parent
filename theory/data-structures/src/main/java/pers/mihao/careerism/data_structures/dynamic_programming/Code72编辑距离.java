package pers.mihao.careerism.data_structures.dynamic_programming;

/**
 * 	72. 编辑距离
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 示例 1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 */
public class Code72编辑距离 {

    public static void main(String[] args) {
//        String word1 = "intention", word2 = "execution";
//        String word1 = "horse", word2 = "ros";
        String word1 = "", word2 = "";
//        System.out.println(new Code72编辑距离().minDistance(word1, word2));
        System.out.println(new Code72编辑距离().minDistance20211203(word1, word2));

    }

    public int minDistance(String word1, String word2) {
        /**
         * dp[i][j] 表示 将0-i 转成0-j需要的最小转换次数
         */
        int[][] dp = new int[word1.length()][word2.length()];
        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                boolean isEquals = word1.charAt(i) == word2.charAt(j);
                if (i == 0 || j == 0) {
                    if (isEquals) {
                        dp[i][j] = Math.max(i, j);
                    }else {
                        if (i == 0 && j == 0) dp[i][j] = 1;
                        else if (i == 0) dp[i][j] = dp[i][j - 1] + 1;
                        else if (j == 0) dp[i][j] = dp[i - 1][j] + 1;
                    }
                } else {
                    if (isEquals) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }else {
                        dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1;
                    }
                }
            }
        }
        return dp[word1.length() - 1][word2.length() - 1];
    }


    private int min(int i, int i1, int i2) {
        int min = i;
        if (i1 < min) min = i1;
        if (i2 < min) min = i2;
        return min;
    }


    /**
     * 完成 差一点就是没有考虑到替换
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance20211203(String word1, String word2){
        int wl1 = word1.length(), wl2 = word2.length();
        if (wl1 == 0 || wl2 == 0) return Math.max(wl1, wl2);
        /**
         * dp[i][j] 表示字符串word1 0-i 与 字符串word2 0-j 的最小编辑距离
         */
        int[][] dp = new int[wl1][wl2];
        for (int i = 0; i < wl1; i++) {
            for (int j = 0; j < wl2; j++) {
                boolean isSame = word1.charAt(i) == word2.charAt(j);
                if (i == 0 && j == 0) {
                    dp[i][j] = isSame ? 0 : 1;
                } else if (i == 0) {
                    dp[i][j] = isSame ? j : Math.min(j, dp[i][j - 1]) + 1;
                } else if (j == 0) {
                    dp[i][j] = isSame ? i : Math.min(i, dp[i - 1][j]) + 1;
                } else if (isSame) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[wl1 - 1][wl2 - 1];
    }

}
