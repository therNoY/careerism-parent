package pers.mihao.careerism.data_structures.dynamic_programming;

/**
 * $$
 * 1143. 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 * 若这两个字符串没有公共子序列，则返回 0。
 * 示例 1:
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace"，它的长度为 3。
 * 示例 2:
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc"，它的长度为 3。
 * 示例 3:
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0。
 * 提示:
 * 1 <= text1.length <= 1000
 * 1 <= text2.length <= 1000
 * 输入的字符串只含有小写英文字符。
 * 链接：https://leetcode-cn.com/problems/longest-common-subsequence
 */
public class Code1143最长公共子序列 {

    public static void main(String[] args) {
        String a = "jmjkbkjkv";
        String b = "bsbininm";
        System.out.println(new Code1143最长公共子序列().longestCommonSubsequence(a, b));
    }

    public int longestCommonSubsequence(String text1, String text2) {
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        /**
         * dp[i][j] 表示text1 0-i月 text2 0-j 的最大公共子串的长度
         */
        int[][] dp = new int[chars1.length][chars2.length];
        for (int i = 0; i < chars1.length; i++) {
            for (int j = 0; j < chars2.length; j++) {
                boolean isSame = chars1[i] == chars2[j];
                if (i == 0 && j == 0) {
                    dp[i][j] = isSame ? 1 : 0;
                }else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] == 1 ? 1 : (isSame ? 1 : 0);
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] == 1 ? 1 : (isSame ? 1 : 0);
                } else {
                    if (isSame) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
        }
        return dp[chars1.length - 1][chars2.length - 1];
    }

    public int longestCommonSubsequence2(String text1, String text2) {
        /**
         * dp[i][j]表示字符串 a长度为i 和字符串长度为j的最长公共子序列
         */
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        boolean isSame;
        for (int i = 0; i <= text1.length(); i++) {
            for (int j = 0; j <= text2.length(); j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }
                isSame = text1.charAt(i - 1) == text2.charAt(j - 1);
                if (isSame) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[text1.length()][text2.length()];
    }

}
