package pers.mihao.careerism.data_structures.dynamic_programming;

/**
 * 91. 解码方法
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 * <p>
 * 示例 1:
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 */
public class Code91解码方法 {

    public static void main(String[] args) {
//        System.out.println(new Code91解码方法().numDecodings("17"));
//        System.out.println(new DecodeWays().numDecodings("10"));
//        System.out.println(new DecodeWays().numDecodings("120"));
//        System.out.println(new DecodeWays().numDecodings("12"));
        System.out.println(new Code91解码方法().numDecodings20211207("226"));
    }


    public int numDecodings(String s) {
        return -1;
    }

    public int numDecodings20211207(String s) {
        int l = s.length();
        if (l == 1) return 1;
        if (l == 2) return Integer.parseInt(s) < 27 ? 2 : 1;
        /**
         * dp[i][0] 最后一位单独解 的长度
         * dp[i][1] 最后一位不单独解 的长度
         */
        int[][] dp = new int[l][2];
        dp[0][0] = 1;
        dp[0][1] = 0;
        dp[1][0] = 1;
        dp[1][1] = Integer.parseInt(s.substring(0, 2)) < 27 ? 2 : 1;
        for (int i = 2; i < l; i++) {
            if (s.charAt(i) > 54 || s.charAt(i - 1) > 50) {
                dp[i][0] = Integer.max(dp[i - 1][0], dp[i - 1][1]);
                dp[i][1] = 0;
            } else {
                dp[i][0] = Integer.max(dp[i - 1][0], dp[i - 1][1]);
                dp[i][1] = dp[i - 2][1] + 2;
            }
        }
        return Integer.max(dp[l - 1][0], dp[l - 1][1]);
    }

}
