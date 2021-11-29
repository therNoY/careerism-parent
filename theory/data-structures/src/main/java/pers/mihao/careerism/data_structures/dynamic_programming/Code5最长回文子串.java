package pers.mihao.careerism.data_structures.dynamic_programming;

/**
 * 5. 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @see Code516最长回文子序列
 */
public class Code5最长回文子串 {

    public static void main(String[] args) {
        System.out.println(new Code5最长回文子串().longestPalindrome20201021("babad"));
    }

    public String longestPalindrome(String s) {
        if (s.length() == 0) return "";
        if (s.length() == 1) return s;
        /**
         * dp[i][j] 表示从i到j是否是回文子串
         */
        boolean[][] dp = new boolean[s.length()][s.length()];
        int l = 0, r = 0;
        // 这里需要斜着遍历
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (i == j) {
                    // 一样就是回文
                    dp[i][j] = true;
                    if (r - l< j - i) {
                        l = i;
                        r = j;
                    }
                }else if (s.charAt(j) == s.charAt(i)) {
                    // 如果相同 距离是1那么就是true 否则看中间的是回文
                    if (j - i == 1 || dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        if (r - l < j - i) {
                            l = i;
                            r = j;
                        }
                    }
                }
            }
        }
        return s.substring(l, r + 1);
    }

    /**
     * 动态规划
     * @param
     * @return
     * @date 20200927
     */
    public String longestPalindrome20200927(String s) {
        int maxLength = Integer.MIN_VALUE, start = 0, end = 0, length;
        if ((length = s.length()) == 0) return "";

        /**
         * dp[i][j] 表示字符串从i到j是否是回文的
         */
        boolean[][] dp = new boolean[length][length];
        char[] chars = s.toCharArray();
        // ↗推导 斜着遍历
        for (int i = length - 1; i >= 0; i++) {
            for (int j = i; j < length; j++) {
                if (j - i > 1) {
                    if (chars[i] == chars[j] && dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                    }else {
                        dp[i][j] = dp[i + 1][j] || dp[i][j - 1];
                    }
                }else if (i == j) {
                    dp[i][j] = true;
                }else if (j - i == 1 && chars[i] == chars[j]) {
                    dp[i][j] = true;
                }
                // 判断是不是最长的字串
                if (dp[i][j] && j - i > maxLength) {
                    start = i;
                    end = j;
                }
            }
        }
        return s.substring(start, end + 1);
    }

    /**
     * 动态规划
     * @param s
     * @return
     * @date 20201021
     */
    public String longestPalindrome20201021(String s) {
        int start = 0, end = 0, length = s.length();
        if (length == 0) return "";
        boolean[][] dp = new boolean[length][length];
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i; j < length; j++) {
                if (j == i) {
                    dp[i][j] = true;
                }else if (i + 1 == j) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                }else {
                    if (dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = true;
                    }
                }
                if (dp[i][j] && j - i > end - start) {
                    start = i;
                    end = j;
                }
            }
        }
        return s.substring(start, end + 1);
    }
}
