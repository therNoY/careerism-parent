package pers.mihao.careerism.data_structures.dynamic_programming.string;

/**
 * 97. 交错字符串
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出: false
 */
public class Code97交错字符串 {

    public static void main(String[] args) {
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbbaccc"));
        System.out.println(new Code97交错字符串().isInterleave20211208l("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(new Code97交错字符串().isInterleave20211208l("aabcc", "dbbca", "aadbbbaccc"));
    }

    public static boolean isInterleave(String s1, String s2, String s3) {
        int s1L = s1.length();
        int s2L = s2.length();
        int s3L = s3.length();
        if (s1L + s2L != s3L) return false;
        /**
         * dp[i][j] 表示前i个s1的字符串 和 前j个s2的字符串能否构成 前i+j的s3的字符串
         */
        boolean[][] dp = new boolean[s1L + 1][s2L + 1];

        dp[0][0] = true;
        for (int i = 1; i <= s1L; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int i = 1; i <= s2L; i++) {
            dp[0][i] = dp[0][i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int i = 1; i <= s1L; i++) {
            for (int j = 1; j <= s2L; j++) {
                dp[i][j] = (s3.charAt(i + j - 1) == s1.charAt(i - 1) && dp[i - 1][j])
                        || (s3.charAt(i + j - 1) == s2.charAt(j - 1) && dp[i][j - 1]);
            }
        }
        return dp[s1L][s2L];
    }



    public boolean isInterleave20211208l(String s1, String s2, String s3) {
        /**
         * dp[i][j][k]表示长度 i->j是否能组成k
         */
        boolean[][][] dp = new boolean[s1.length() + 1][s2.length() + 1][s3.length() + 1];

        for (int i = 0; i < s1.length() + 1; i++) {
            for (int j = 0; j < s2.length() + 1; j++) {
                for (int k = 0; k < s3.length() + 1; k++) {
                    if (i + j == k){
                        if (i == 0 && j == 0 && k == 0){
                            dp[i][j][k] = true;
                        } else if (i == 0) {
                            dp[i][j][k] = dp[i][j - 1][k - 1] && s2.charAt(j - 1) == s3.charAt(k - 1);
                        } else if (j == 0) {
                            dp[i][j][k] = dp[i - 1][j][k - 1] && s1.charAt(i - 1) == s3.charAt(k - 1);
                        } else if (s1.charAt(i - 1) == s3.charAt(k - 1) && s2.charAt(j - 1) == s3.charAt(k - 1)) {
                            dp[i][j][k] = dp[i - 1][j][k - 1] || dp[i][j - 1][k - 1];
                        } else if (s1.charAt(i - 1) == s3.charAt(k - 1)) {
                            dp[i][j][k] = dp[i - 1][j][k - 1];
                        } else if (s2.charAt(j - 1) == s3.charAt(k - 1)) {
                            dp[i][j][k] = dp[i][j - 1][k - 1];
                        } else {
                            dp[i][j][k] = false;
                        }
                    }
                }
            }
        }
        return dp[s1.length()][s2.length()][s3.length()];
    }
}
