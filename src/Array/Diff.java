package Array;

import java.util.BitSet;
import java.util.HashSet;

public class Diff {

    //128. 最长连续序列 https://leetcode-cn.com/problems/longest-consecutive-sequence/
    public static int longestConsecutive(int[] nums) {
        int result = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            int num = nums[i];
            if (!set.contains(num)) continue;
            while (set.contains(num)) {
                count++;
                set.remove(num);
                num--;
            }
            num = nums[i] + 1;
            while (set.contains(num)) {
                count++;
                set.remove(num);
                num++;
            }
            result = Math.max(result, count);
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));

    }

}
