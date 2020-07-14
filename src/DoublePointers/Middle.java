package DoublePointers;


import java.util.*;

public class Middle {

    //3. 无重复字符的最长子串 https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) return s.length();
        int i = 0, j = 0, result = 1;
        Set<Character> set = new HashSet<>(26);
        set.add(s.charAt(0));
        while (j < s.length()) {
            if (i == j) {
                j++;
                continue;
            }
            char c = s.charAt(j);
            while (set.contains(c)) {
                set.remove(s.charAt(i));
                i++;
            }
            set.add(s.charAt(j));
            result = Math.max(result, j - i + 1);
            j++;
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("dvdf"));
//        System.out.println(rob1(new int[]{2, 3, 2}));
    }

}
