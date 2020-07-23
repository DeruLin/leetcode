package String;

import java.util.*;

public class Diff {

    //97. 交错字符串 https://leetcode-cn.com/problems/interleaving-string/
    public static Map<String, Boolean> map;

    public static boolean isInterleave(String s1, String s2, String s3) {
        map = new HashMap<>();
        return helperFor97(s1.toCharArray(), 0, s2.toCharArray(), 0, s3, 0);
    }

    public static boolean helperFor97(char[] c1, int start1, char[] c2, int start2, String target, int start3) {
        if (start3 >= target.length()) {
            return start1 == c1.length && start2 == c2.length;
        }
        String key = start1 + "," + start2 + "," + start3;
        if (map.containsKey(key)) return map.get(key);
        char c = target.charAt(start3);
        boolean flag1 = start1 < c1.length && c == c1[start1];
        boolean flag2 = start2 < c2.length && c == c2[start2];
        boolean result;
        if (flag1 && flag2)
            result = helperFor97(c1, start1 + 1, c2, start2, target, start3 + 1) || helperFor97(c1, start1, c2, start2 + 1, target, start3 + 1);
        else if (flag1) {
            result = helperFor97(c1, start1 + 1, c2, start2, target, start3 + 1);
        } else if (flag2) {
            result = helperFor97(c1, start1, c2, start2 + 1, target, start3 + 1);
        } else {
            result = false;
        }
        map.put(key, result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(isInterleave("aabcc",
                "dbbca",
                "aadbbcbcac"));
    }
}
