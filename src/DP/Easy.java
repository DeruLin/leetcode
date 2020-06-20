package DP;

import java.util.*;

public class Easy {

    //最大子序和 https://leetcode-cn.com/problems/maximum-subarray/
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) return 0;
        int result = Integer.MIN_VALUE;
        int dp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp = Math.max(dp + nums[i], nums[i]);
            result = Math.max(dp, result);
        }
        return result;
    }


    public static void main(String[] args) {

    }

}
