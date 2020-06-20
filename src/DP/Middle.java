package DP;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Middle {

    //零钱兑换 https://leetcode-cn.com/problems/coin-change/
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = amount + 1;
            for (int v : coins) {
                if (i - v < 0) continue;
                dp[i] = Math.min(dp[i], dp[i - v] + 1);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }


    // 零钱兑换 II https://leetcode-cn.com/problems/coin-change-2/
    public static int change(int amount, int[] coins) {
        int[][] dp = new int[amount + 1][coins.length + 1];
        for (int i = 0; i <= amount; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j <= coins.length; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i <= amount; i++) {
            for (int j = 1; j <= coins.length; j++) {
                if (i - coins[j - 1] >= 0)
                    dp[i][j] = dp[i - coins[j - 1]][j] + dp[i][j - 1];
                else
                    dp[i][j] = dp[i][j - 1];
            }
        }
        return dp[amount][coins.length];
    }

    //最长上升子序列 https://leetcode-cn.com/problems/longest-increasing-subsequence/
    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int maxResult = 0;
        dp[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            dp[i] = -1;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j])
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
            }
            if (dp[i] == -1) dp[i] = 1;
            if (dp[i] > maxResult) maxResult = dp[i];
        }
        return maxResult;
    }

    //分割等和子集 https://leetcode-cn.com/problems/partition-equal-subset-sum/
    public static boolean canPartition(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        if (totalSum % 2 == 1) return false;
        boolean[] dp = new boolean[totalSum / 2 + 1];
        for (int j = 0; j < totalSum / 2 + 1; j++) {
            dp[j] = false;
        }
        dp[0] = true;

        for (int i = 1; i < nums.length + 1; i++) {
            for (int j = totalSum / 2; j >= 1; j--) {
                if (j >= nums[i - 1])
                    dp[j] = dp[j - nums[i - 1]] || dp[j];
            }
        }
        return dp[totalSum / 2];
    }

    public static List<Integer> allSum;

    public static boolean canPartition1(int[] nums) {
        allSum = new ArrayList<>();
        canPartitionHelper(nums, 0, 0);
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        for (int sum : allSum) {
            if (totalSum - sum == sum)
                return true;
        }
        return false;
    }

    public static void canPartitionHelper(int[] nums, int start, int sum) {
        if (start >= nums.length) {
            allSum.add(sum);
            return;
        }
        int curr = nums[start];
        canPartitionHelper(nums, start + 1, sum);
        canPartitionHelper(nums, start + 1, sum + curr);
    }


    public static void main(String[] args) {
        System.out.println(change(5, new int[]{1, 2, 5}));
    }

}
