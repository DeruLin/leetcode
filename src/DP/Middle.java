package DP;


import simple.Tree;

import java.util.*;

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
        for (int start = len - 1; start >= 0; start--) {
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
        return dp[0][len - 1][0] > dp[0][len - 1][1];
    }

    public static int rob1(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int[] dp = new int[nums.length];
            dp[0] = nums[i];
            if (i + 1 < nums.length)
                dp[1] = Math.max(nums[i], nums[i + 1]);
            else
                dp[1] = Math.max(nums[i], nums[0]);
            int count = 2;

            while (count < nums.length - 1) {
                int index = count + i;
                if (index > nums.length - 1) index = index - (nums.length);
                dp[count] = Math.max(dp[count - 1], nums[index] + dp[count - 2]);
                count++;
            }
            if (dp[nums.length - 2] > result) result = dp[nums.length - 2];
        }
        return result;
    }

    //打家劫舍 III https://leetcode-cn.com/problems/house-robber-iii/

    public static Map<TreeNode, Integer> memo;

    public static int rob(TreeNode root) {
        memo = new HashMap<>();
        return traverseForRob(root);
    }

    public static int traverseForRob(TreeNode node) {
        if (node == null) return 0;
        if (memo.containsKey(node)) {
            return memo.get(node);
        }
        int a = node.val;
        if (node.left != null) {
            a += traverseForRob(node.left.left) + traverseForRob(node.left.right);
        }
        if (node.right != null) {
            a += traverseForRob(node.right.left) + traverseForRob(node.right.right);
        }
        int b = traverseForRob(node.left) + traverseForRob(node.right);
        int result = Math.max(a, b);
        memo.put(node, result);
        return result;
    }

    //837. 新21点 https://leetcode-cn.com/problems/new-21-game/
    public static double new21Game(int N, int K, int W) {
        double[] dp = new double[K + W];
        double tmp = 0;
        for (int i = K; i < K + W; i++) {
            if (i <= N) dp[i] = 1;
            else dp[i] = 0;
            tmp += dp[i];
        }
        for (int i = K - 1; i >= 0; i--) {
            dp[i] = tmp / W;
            tmp = tmp - dp[i + W] + dp[i];
        }
        return dp[0];
    }

    //688. “马”在棋盘上的概率 https://leetcode-cn.com/problems/knight-probability-in-chessboard/
    public static double knightProbability(int N, int K, int r, int c) {
        double[][][] dp = new double[K + 1][N][N];
        dp[0][r][c] = 1;
        for (int i = 1; i < K; i++) {
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    double p1 = (x - 2 < 0 || y - 1 < 0) ? 0 : dp[i - 1][x - 2][y - 1];
                    double p2 = (x - 2 < 0 || y + 1 >= N) ? 0 : dp[i - 1][x - 2][y + 1];
                    double p3 = (x + 2 >= N || y - 1 < 0) ? 0 : dp[i - 1][x + 2][y - 1];
                    double p4 = (x + 2 >= N || y + 1 >= N) ? 0 : dp[i - 1][x + 2][y + 1];
                    double p5 = (x - 1 < 0 || y - 2 < 0) ? 0 : dp[i - 1][x - 1][y - 2];
                    double p6 = (x - 1 < 0 || y + 2 >= N) ? 0 : dp[i - 1][x - 1][y + 2];
                    double p7 = (x + 1 >= N || y - 2 < 0) ? 0 : dp[i - 1][x + 1][y - 2];
                    double p8 = (x + 1 >= N || y + 2 >= N) ? 0 : dp[i - 1][x + 1][y + 2];
                    dp[i][x][y] = (p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8) / 8;
                }
            }
        }

        double res = 0; //这里的答案就是要求出最后一步，落在棋盘上各个位置上概率的总和
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                res += dp[K][i][j];
            }
        return res;
    }


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
//        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(3);
//        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(1);
        System.out.println(knightProbability(3, 2, 0, 0));
//        System.out.println(rob1(new int[]{2, 3, 2}));
    }

}
