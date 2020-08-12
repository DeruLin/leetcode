package String;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Middle {

    //面试题 17.13. 恢复空格 https://leetcode-cn.com/problems/re-space-lcci/
    public static Map<String, Boolean> lookMemo;
    public static int[][] reSpaceMemo;

    public static int respace(String[] dictionary, String sentence) {
        if (sentence.isEmpty()) return 0;
        lookMemo = new HashMap<>(10000);
//        int len = sentence.length()+1;
//        reSpaceMemo = new int[len][len];
//        for (int i = 0; i < len; i++) {
//            for (int j = 0; j < len; j++)
//                reSpaceMemo[i][j] = -1;
//        }
//        return respaceHelper(dictionary, sentence, 0, 1);
        int len = sentence.length();
        int[][] dp = new int[len][len];
        for (int start = len - 1; start >= 0; start--) {
            for (int end = start; end < len; end++) {
                String tmp = sentence.substring(start, end + 1);
                System.out.println(tmp);
                if (start == end) {
                    if (lookDic(dictionary, tmp))
                        dp[start][end] = 0;
                    else dp[start][end] = 1;
                    continue;
                }
                if (lookDic(dictionary, tmp)) {
                    dp[start][end] = 0;
                } else {
                    int aa = Math.min(dp[start + 1][end] + dp[start][start], dp[start][end - 1] + dp[end][end]);
                    dp[start][end] = aa;
                }
            }
        }
        return dp[0][len - 1];
    }

    public static int respaceHelper(String[] dictionary, String sentence, int start, int end) {
        if (reSpaceMemo[start][end] >= 0) return reSpaceMemo[start][end];
        int result = Integer.MAX_VALUE;
        if (end >= sentence.length()) {
            String tmp = sentence.substring(start);
            if (lookDic(dictionary, tmp)) {
                return 0;
            } else {
                return sentence.length() - start;
            }
        }

        for (int i = end; i < sentence.length(); i++) {
            int result1;
            String tmp = sentence.substring(start, end);
            if (lookDic(dictionary, tmp)) {
                result1 = respaceHelper(dictionary, sentence, end, end + 1);
            } else {
                result1 = end - start + respaceHelper(dictionary, sentence, end, end + 1);
            }
            int result2 = respaceHelper(dictionary, sentence, start, end + 1);
            result = Math.min(result, Math.min(result1, result2));
        }
        reSpaceMemo[start][end] = result;
        return result;
    }

    public static boolean lookDic(String[] dictionary, String word) {
        if (lookMemo.containsKey(word))
            return lookMemo.get(word);
        boolean result = false;
        for (String s : dictionary) {
            if (s.equals(word)) {
                result = true;
                break;
            }
        }
        lookMemo.put(word, result);
        return result;
    }

    //415. 字符串相加 https://leetcode-cn.com/problems/add-strings/
    public String addStrings(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        int i = 0;
        boolean flag = false;
        StringBuilder sb = new StringBuilder();
        while (i < len1 && i < len2) {
            int c1 = num1.charAt(len1 - 1 - i) - '0';
            int c2 = num2.charAt(len2 - 1 - i) - '0';
            int sum = c1 + c2;
            if (flag) sum++;
            if (sum >= 10) {
                flag = true;
                sum -= 10;
            } else flag = false;
            sb.insert(0, sum);
            i++;
        }
        while (i < len1) {
            int c1 = num1.charAt(len1 - 1 - i) - '0';
            if (flag) c1++;
            if (c1 >= 10) {
                flag = true;
                c1 -= 10;
            } else flag = false;
            sb.insert(0, c1);
            i++;
        }
        while (i < len2) {
            int c2 = num2.charAt(len2 - 1 - i) - '0';
            if (flag) c2++;
            if (c2 >= 10) {
                flag = true;
                c2 -= 10;
            } else flag = false;
            sb.insert(0, c2);
            i++;
        }
        if (flag)
            sb.insert(0, 1);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(respace(new String[]{"looked", "just", "like", "her", "brother", "je"}, "jesslooked"));
    }

}
