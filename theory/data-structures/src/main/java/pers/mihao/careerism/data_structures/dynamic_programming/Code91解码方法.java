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
//        System.out.println(new Code91解码方法().numDecodings20211208("17"));
//        System.out.println(new Code91解码方法().numDecodings20211208("2611055971756562"));
        System.out.println(new Code91解码方法().numDecodings20211208("30"));
    }


    public int numDecodings(String s) {
        return -1;
    }


    public int numDecodings20211208(String s) {
        int l = s.length();
        for (int i = 0; i < l; i++) {
            if (s.charAt(i) == 48 && (i == 0 || (s.charAt(i - 1) != 49 && s.charAt(i - 1) != 50))) {
                return 0;
            }
        }
        if (l == 1) return 1;
        if (l == 2) return (Integer.parseInt(s) < 27 && s.charAt(1) != 48) ? 2 : 1;
        int[] dp = new int[l];
        dp[0] = 1;
        dp[1] = (Integer.parseInt(s.substring(0 , 2)) < 27 && s.charAt(1) != 48) ? 2 : 1;
        for (int i = 2; i < l; i++) {
            if ((s.charAt(i) > 54 && s.charAt(i - 1) != 49) || s.charAt(i - 1) > 50 || s.charAt(i - 1) == 48) {
                dp[i] = dp[i - 1];
            } else if (s.charAt(i) == 48) {
                dp[i] = dp[i - 2];
            } else {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }
        return dp[l - 1];
    }

}
