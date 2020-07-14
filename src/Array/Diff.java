package Array;

import java.util.BitSet;
import java.util.HashSet;

public class Diff {

    //128. 最长连续序列 https://leetcode-cn.com/problems/longest-consecutive-sequence/
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            int nums
            int num = nums[i];
            if (!set.contains(num)) continue;
            while(set.contains(num)){
                nu
            }
        }

    }


    public static void main(String[] args) {
        System.out.println(isStraight(new int[]{1, 2, 3, 4, 5}));

    }

}
