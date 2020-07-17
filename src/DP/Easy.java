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

    //买卖股票的最佳时机 https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
    public static int maxProfit(int[] prices) {
        if (prices.length <= 1) return 0;
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][0]);
            dp[i][1] = Math.max(-prices[i], dp[i - 1][1]);
        }
        return dp[prices.length - 1][0];
    }

    //买卖股票的最佳时机 II https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
    public int maxProfit2(int[] prices) {
        if (prices.length <= 1) return 0;
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][0]);
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
        }
        return dp[prices.length - 1][0];
    }

    public static void main(String[] args) {
//        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

}
