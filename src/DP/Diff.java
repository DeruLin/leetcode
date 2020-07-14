package DP;

import java.util.concurrent.ThreadPoolExecutor;

public class Diff {

    //编辑距离 https://leetcode-cn.com/problems/edit-distance/
    public static int minDistance(String word1, String word2) {
        int lenI = word1.length() + 1;
        int lenJ = word2.length() + 1;
        int[][] dp = new int[lenI][lenJ];
        for (int i = 0; i < lenI; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < lenJ; j++) {
            dp[0][j] = j;
        }
//        dp[0][0] = 0;
        for (int i = 1; i < lenI; i++) {
            for (int j = 1; j < lenJ; j++) {
                char c1 = word1.charAt(i - 1);
                char c2 = word2.charAt(j - 1);
                if (c1 == c2)
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
            }
        }
        return dp[lenI - 1][lenJ - 1];
    }

    // 买卖股票的最佳时机 IV https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/
    public static int maxProfit(int k, int[] prices) {
        if (prices.length <= 1) return 0;
        if (k > prices.length / 2) k = prices.length / 2;
        int[][][] dp = new int[prices.length][k + 1][2];
        for (int j = 0; j <= k; j++) {
            dp[0][j][0] = 0;
            dp[0][j][1] = -prices[0];
        }
        for (int i = 1; i < prices.length; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = 0;
        }
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][1] + prices[i], dp[i - 1][j][0]);
                dp[i][j][1] = Math.max(dp[i - 1][j - 1][0] - prices[i], dp[i - 1][j][1]);
            }
        }
        return dp[prices.length - 1][k][0];
    }

    //44. 通配符匹配 https://leetcode-cn.com/problems/wildcard-matching/
    public static boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
        boolean allStar = true;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) != '*') {
                allStar = false;
            }
            dp[i + 1][0] = allStar;

        }
        for (int j = 0; j < s.length(); j++) {
            dp[0][j + 1] = false;
        }
        dp[0][0] = true;
        for (int i = 1; i <= p.length(); i++) {
            for (int j = 1; j <= s.length(); j++) {
                char cp = p.charAt(i - 1);
                char cs = s.charAt(j - 1);
                if (cp == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (cp == '*') {
                    dp[i][j] = dp[i - 1][j - 1] || dp[i][j - 1] || dp[i - 1][j];
                }
                if (cp == cs) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[p.length()][s.length()];
    }


    public static void main(String[] args) {
        System.out.println(isMatch("a", "a*"));
    }

}
