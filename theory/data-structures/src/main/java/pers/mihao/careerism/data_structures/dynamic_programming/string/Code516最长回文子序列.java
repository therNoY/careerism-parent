package pers.mihao.careerism.data_structures.dynamic_programming.string;

/**
 * 516. 最长回文子序列 给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。
 * <p>
 * 示例 1: 输入:
 * <p>
 * "bbbab" 输出: 4 一个可能的最长回文子序列为 "bbbb"。 示例 2: 输入: "cbbd" 输出: 2 一个可能的最长回文子序列为 "bb"。 提示： 1 <= s.length <= 1000 s 只包含小写英文字母
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/longest-palindromic-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author hspcadmin
 * @see Code5最长回文子串
 * @see Code300最长上升子序列
 */
public class Code516最长回文子序列 {

    public static void main(String[] args) {
        System.out.println(new Code516最长回文子序列().longestPalindromeSubseq("bbbab"));
        System.out.println(new Code516最长回文子序列().longestPalindromeSubseq20211130("bbbab"));
    }

    public int longestPalindromeSubseq(String s) {
        if (s.length() < 2) {
            return s.length();
        }
        int[][] dp = new int[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = j - i <= 2 ? j - i + 1 : dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }

    /**
     * @param s
     * @return
     * @date 20200927
     */
    public int longestPalindromeSubseq2(String s) {
        int length;
        if ((length = s.length()) == 0) {
            return 0;
        }
        int[][] dp = new int[length][length];
        for (int i = length - 1; i >= 0; i++) {
            for (int j = i; j < length; j++) {
                if (i != j) {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
                    }
                } else {
                    dp[i][j] = 1;
                }

            }
        }

        return dp[length - 1][length - 1];
    }

    public int longestPalindromeSubseq20211130(String s) {
        int l = s.length();
        if (l == 0 || l == 1) return l;
        /**
         * dp[i][j] 表示字符串i-j的最大回文子序列的长度
         */
        int[][] dp = new int[l][l];
        for (int i = l - 1; i >= 0; i--) {
            for (int j = i; j < l; j++) {
                if (i != j) {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
                    }
                } else {
                    dp[i][j] = 1;
                }
            }
        }
        return dp[0][l - 1];
    }
}
