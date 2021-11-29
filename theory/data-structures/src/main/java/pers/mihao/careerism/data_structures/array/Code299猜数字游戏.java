package pers.mihao.careerism.data_structures.array;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import pers.mihao.common.util.ScanUtil;

/**
 * 299. 猜数字游戏 你正在和你的朋友玩 猜数字（Bulls and Cows）游戏：你写下一个数字让你的朋友猜。 每次他猜测后，你给他一个提示，告诉他有多少位数字和确切位置都猜对了（称为“Bulls”, 公牛），
 * 有多少位数字猜对了但是位置不对（称为“Cows”, 奶牛）。你的朋友将会根据提示继续猜，直到猜出秘密数字。 请写出一个根据秘密数字和朋友的猜测数返回提示的函数，用 A 表示公牛，用 B 表示奶牛。
 * 请注意秘密数字和朋友的猜测数都可能含有重复数字。
 * <p>
 * 示例 1: 输入: secret = "1807", guess = "7810" 输出: "1A3B" 解释: 1 公牛和 3 奶牛。公牛是 8，奶牛是 0, 1 和 7。 示例 2: 输入: secret = "1123",
 * guess = "0111" 输出: "1A1B" 解释: 朋友猜测数中的第一个 1 是公牛，第二个或第三个 1 可被视为奶牛。  
 * <p>
 * 说明: 你可以假设秘密数字和朋友的猜测数都只包含数字，并且它们的长度永远相等。
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/bulls-and-cows 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author hspcadmin
 */
public class Code299猜数字游戏 {

    public static void main(String[] args) {

        Code299猜数字游戏 code299猜数字游戏 = new Code299猜数字游戏();


        StringBuilder a = new StringBuilder(String.valueOf((int) (Math.random() * 9999)));
        AtomicInteger count = new AtomicInteger();
        while (a.length() < 4) {
            a.insert(0, "0");
        }
        ScanUtil.run(s -> {
            String res = code299猜数字游戏.getHint2(a.toString(), s);
            System.out.println("输入: " + s + " 结果: " + res);
            count.incrementAndGet();
            if (res.equals("4A0B")) {
                System.out.println("^_^ 你猜对了: " + a + "用了" + count.get() + "次");
                System.exit(0);
            }
        });

//        String secret = "11", guess = "10";
//        String res = code299猜数字游戏.getHint(secret, guess);
//        res = code299猜数字游戏.getHint2(secret, guess);
    }

    /**
     * 正确答案
     *
     * @param secret
     * @param guess
     * @return
     */
    public String getHint(String secret, String guess) {
        int aCount = 0, bCount = 0, si = 0, gi = 0;

        char[] secretChars = secret.toCharArray();
        char[] guessChars = guess.toCharArray();
        Map<Character, Integer> map = new HashMap<>(secretChars.length);

        Integer times;
        Integer res;
        for (; si < secretChars.length; si++, gi++) {
            if (secretChars[si] == guessChars[gi]) {
                aCount++;
            } else {
                if ((times = map.get(secretChars[gi])) == null) {
                    map.put(secretChars[gi], 1);
                } else {
                    map.put(secretChars[gi], times + 1);
                }
            }
        }

        for (si = 0, gi = 0; si < secretChars.length; si++, gi++) {
            if (secretChars[si] != guessChars[gi]) {
                if ((res = map.get(guessChars[gi])) != null && res > 0) {
                    bCount++;
                    map.put(guessChars[gi], res - 1);
                }
            }
        }

        return aCount + "A" + bCount + "B";

    }

    public String getHint2(String secret, String guess) {
        int aCount = 0, bCount = 0;
        if (secret == null || guess == null) return aCount + "A" + bCount + "B";
        char[] sArray = secret.toCharArray(), gArray = guess.toCharArray();
        Map<Character, Integer> map = new HashMap<>(16);
        for (int i = 0; i < sArray.length; i++) {
            char s = sArray[i], g = gArray[i];
            if (s == g) {
                aCount ++;
            } else {
                map.merge(s, 1, Integer::sum);
            }
        }
        for (int i = 0; i < sArray.length; i++) {
            char s = sArray[i], g = gArray[i];
            if (s == g) {
                continue;
            }
            Integer sCount;
            if ((sCount = map.get(g)) != null && sCount > 0) {
                map.put(g, sCount - 1);
                bCount++;
            }
        }
        return aCount + "A" + bCount + "B";
    }
}
