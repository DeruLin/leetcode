package DP;

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


    public static void main(String[] args) {
        System.out.println(maxProfit(2, new int[]{3, 2, 6, 5, 0, 3}));
    }

}
