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

    //最长公共子序列 https://leetcode-cn.com/problems/longest-common-subsequence/
    public static int longestCommonSubsequence(String text1, String text2) {
        int lenI = text1.length() + 1;
        int lenJ = text2.length() + 1;
        int[][] dp = new int[lenI][lenJ];
        for (int i = 0; i < lenI; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j < lenJ; j++) {
            dp[0][j] = 0;
        }
        for (int i = 1; i < lenI; i++) {
            for (int j = 1; j < lenJ; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return dp[lenI - 1][lenJ - 1];
    }

    //最长回文子序列 https://leetcode-cn.com/problems/longest-palindromic-subsequence/
    public static int longestPalindromeSubseq(String s) {
        int len = s.length() + 1;
        String s_reverse = "";
        for (int i = len - 2; i >= 0; i--) {
            s_reverse += s.charAt(i);
        }
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j < len; j++) {
            dp[0][j] = 0;
        }
        for (int i = 1; i < len; i++) {
            for (int j = 1; j < len; j++) {
                if (s.charAt(i - 1) == s_reverse.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return dp[len - 1][len - 1];
    }

    //石子游戏 https://leetcode-cn.com/problems/stone-game/
    public static boolean stoneGame(int[] piles) {
        int len = piles.length;
        int[][][] dp = new int[len][len][2];
        for (int start = 0; start < len; start++) {
            for (int end = 0; end < len; end++) {
                if (start == end) {
                    dp[start][end][0] = piles[start];
                    dp[start][end][1] = 0;
                }
            }
        }
        for (int start = len-1; start >= 0; start--) {
            for (int end = 0; end < len; end++) {
                if (start >= end) continue;
                int left = dp[start + 1][end][1] + piles[start];
                int right = dp[start][end - 1][1] + piles[end];
                if (left > right) {
                    dp[start][end][0] = left;
                    dp[start][end][1] = dp[start + 1][end][0];
                } else {
                    dp[start][end][0] = right;
                    dp[start][end][1] = dp[start][end - 1][0];
                }
            }
        }
        return dp[0][len-1][0] > dp[0][len-1][1];
    }


    public static void main(String[] args) {
        System.out.println(stoneGame(new int[]{3,2,10,4}));
    }

}
