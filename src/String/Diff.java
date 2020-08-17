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

    public static String[] minHeap;
    public static int heapCount;
    public static Map<String, Integer> countMap;

    public static List<String> topKFrequent(String[] words, int k) {
        minHeap = new String[k];
        heapCount = 0;
        countMap = new HashMap<>(words.length * 2);
        for (int i = 0; i < words.length; i++) {
            int count = countMap.getOrDefault(words[i], 0);
            countMap.put(words[i], count + 1);
        }
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            if (heapCount < k) {
                minHeap[heapCount] = entry.getKey();
                heapFixUp(heapCount);
                heapCount++;
            } else {
                if (compare1(entry.getKey(), minHeap[0])) {
                    minHeap[0] = entry.getKey();
                    heapFixDown(0);
                }
            }
        }
        List<String> result = new ArrayList<>();
        while (heapCount > 0) {
            result.add(minHeap[0]);
            minHeap[0] = minHeap[heapCount - 1];
            heapFixDown(0);
            heapCount--;
        }
        Collections.reverse(result);
        return result;
    }

    public static void buildHeap() {
        for (int i = heapCount - 1; i > 0; i--) {
            heapFixUp(i);
        }
    }

    public static void heapFixUp(int i) {
        int child = i;
        int parent = (child - 1) / 2;
        while (child > 0) {
            if (compare(parent, child)) {
                swap(child, parent);
            }
            child = parent;
            parent = (child - 1) / 2;
        }
    }

    public static void heapFixDown(int i) {
        int parent = i;
        int child1 = parent * 2 + 1;
        int child2 = parent * 2 + 2;
        while (child1 < heapCount) {
            if (child2 < heapCount && compare(child1, child2)) {
                child1 = child2;
            }
            if (compare(parent, child1)) {
                swap(parent, child1);
                parent = child1;
            } else break;
            child1 = parent * 2 + 1;
            child2 = parent * 2 + 2;
        }
    }

    public static boolean compare(int i, int j) {
        int count1 = countMap.get(minHeap[i]);
        int count2 = countMap.get(minHeap[j]);
        if (count1 > count2) return true;
        else if (count1 < count2) return false;
        else {
            int c = minHeap[i].compareTo(minHeap[j]);
            return c < 0;
        }
    }

    public static boolean compare1(String i, String j) {
        int count1 = countMap.get(i);
        int count2 = countMap.get(j);
        if (count1 > count2) return true;
        else if (count1 < count2) return false;
        else {
            int c = i.compareTo(j);
            return c < 0;
        }
    }


    public static void swap(int i, int j) {
        String tmp = minHeap[i];
        minHeap[i] = minHeap[j];
        minHeap[j] = tmp;
    }

    //最长回文子串 https://leetcode-cn.com/problems/longest-palindromic-substring/
    public static String longestPalindrome(String s) {
        if (s.length() == 0) return "";
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i)).append("#");
        }
        String str = sb.toString();
        String result = "";
        int maxLen = Integer.MIN_VALUE;
        int c = 0;
        int[] l = new int[str.length()];
        int[] r = new int[str.length()];
        l[0] = 0;
        r[0] = 0;
        int tmpL, tmpR, tmpLen;
        for (int i = 1; i < str.length(); i++) {
            if (i > r[i - 1]) {
                tmpL = i - 1;
                tmpR = i + 1;
                while (tmpL >= 0 && tmpR < str.length() && str.charAt(tmpL) == str.charAt(tmpR)) {
                    tmpL -= 1;
                    tmpR += 1;
                }
                tmpL += 1;
                tmpR -= 1;
                tmpLen = tmpR - tmpL + 1;
            } else {
                int j = c - (i - c);
                if (l[j] > l[i - 1]) {
                    tmpLen = r[j] - l[j] + 1;
                    tmpL = i - (j - l[j]);
                    tmpR = i + (r[j] - j);
                } else if (l[j] < l[i - 1]) {
                    tmpL = i - (r[i - 1] - i);
                    tmpR = r[i - 1];
                    tmpLen = tmpR - tmpL + 1;
                } else {
                    tmpL = i - 1;
                    tmpR = i + 1;
                    while (tmpL >= 0 && tmpR < str.length() && str.charAt(tmpL) == str.charAt(tmpR)) {
                        tmpL -= 1;
                        tmpR += 1;
                    }
                    tmpL += 1;
                    tmpR -= 1;
                    tmpLen = tmpR - tmpL + 1;
                }
            }
            l[i] = tmpL;
            r[i] = tmpR;
            if (r[i] > r[i - 1])
                c = i;
            result = tmpLen > maxLen ? str.substring(tmpL, tmpR) : result;
            maxLen = Math.max(maxLen, tmpLen);
        }
        return result.replace("#", "");
    }

    //28. 实现 strStr() https://leetcode-cn.com/problems/implement-strstr/ KMP算法
    public static int strStr(String haystack, String needle) {
        if (needle.length()==0) return 0;
        int[] next = getNextArray(needle);
        int i = 0, j = 0;
        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                if (j == needle.length() - 1) {
                    return i - needle.length() + 1;
                }
                i++;
                j++;
            } else {
                i = i - next[j];
                j = 0;
            }
        }
        return -1;
    }

    public static int[] getNextArray(String needle) {
        if (needle.length()==1) return new int[]{-1};
        if (needle.length()==2) return new int[]{-1,0};
        int[] next = new int[needle.length()];
        //以下两行人为规定
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        while (i < needle.length()) {
            int jump = i - 1;
            int c1 = needle.charAt(next[jump]);
            int c2 = needle.charAt(i - 1);
            while (c1 != c2) {
                jump = next[jump];
                if (next[jump] == -1) break;
                c1 = needle.charAt(next[jump]);
            }
            if (c1 == c2) {
                next[i] = next[jump] + 1;
            } else {
                next[i] = 0;
            }
            i++;
        }
        return next;
    }


    public static void main(String[] args) {
        System.out.println(strStr("",""));
    }
}
