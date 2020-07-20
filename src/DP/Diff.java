package DP;

import java.util.Arrays;

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

    //403. 青蛙过河 https://leetcode-cn.com/problems/frog-jump/
    public static boolean canCross(int[] stones) {
        if (stones.length <= 1) return true;
        int len = stones.length;
        boolean[][] dp = new boolean[1200][len];
        dp[0][0] = true;
        if (stones[1] != 1) return false;
        dp[1][1] = true;
        for (int j = 1; j < len; j++) {
            for (int i = 1; i < 1200; i++) {

                if (dp[i][j]) {
                    for (int k = j + 1; k < len; k++) {
                        int distance = stones[k] - stones[j];
                        System.out.print("from dp[" + (i) + "][" + j + "] ");
                        if (i - 1 == distance) {
                            System.out.print("dp[" + (i - 1) + "][" + (k) + "]");
                            dp[i - 1][k] = true;
                        }
                        if (i == distance) {
                            System.out.print("dp[" + (i) + "][" + (k) + "]");
                            dp[i][k] = true;
                        }
                        if (i + 1 == distance) {
                            System.out.print("dp[" + (i + 1) + "][" + (k) + "]");
                            dp[i + 1][k] = true;
                        }
                        System.out.print("\n");
                    }
                }
            }
        }
        for (int i = 1; i < 1200; i++) {
            if (dp[i][len - 1]) return true;
        }
        return false;
    }


    //546. 移除盒子 https://leetcode-cn.com/problems/remove-boxes/
    public static int[] copy;

    public static int removeBoxes(int[] boxes) {
        copy = Arrays.copyOf(boxes, boxes.length);
        return removeBoxesHelper(boxes, 0);
    }

    public static int removeBoxesHelper(int[] boxes, int score) {
        boolean isAllUsed = true;
        for (int box : boxes) {
            if (box != -1) {
                isAllUsed = false;
                break;
            }
        }
        if (isAllUsed) return score;
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < boxes.length; i++) {
            if (boxes[i] == -1) continue;
            int j = i + 1;
            int deleteCount = 0;
            for (; j < boxes.length; j++) {
                if (boxes[j] == -1) {
                    boxes[j] = -2;
                    deleteCount++;
                    continue;
                }
                if (boxes[j] != boxes[i]) break;
            }
            j--;
            int k = j - i + 1 - deleteCount;
            for (int x = i; x <= j; x++) {
                boxes[x] = -1;
            }
            int tmp = removeBoxesHelper(boxes, (int) (score + Math.pow(k, 2)));
            result = Math.max(tmp, result);
            //回溯
            for (int x = i; x <= j; x++) {
                if (boxes[x] == -2) boxes[x] = -1;
                else
                    boxes[x] = copy[x];
            }
        }
        return result;
    }

    //887. 鸡蛋掉落 https://leetcode-cn.com/problems/super-egg-drop/
    public int superEggDrop(int K, int N) {
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(2, new int[]{3, 2, 6, 5, 0, 3}));
    }

}
