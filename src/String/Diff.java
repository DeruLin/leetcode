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


    public static void main(String[] args) {
        System.out.println(topKFrequent(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4));
        Queue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -o1.compareTo(o2);
            }
        });

    }
}
