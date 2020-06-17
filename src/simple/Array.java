package simple;

import java.util.*;

public class Array {

    public static void main(String[] args) {
        int nums[] = new int[]{1, 2, 3, 4, 5};
        System.out.println(maxProfit(nums));
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length <= 1) return nums.length;
        int j = 0, count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                count++;
                nums[j + 1] = nums[i];
                j++;
            }
        }
        return count;
    }

    // 买卖股票的最佳时机 II https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
    //暴力法 加记忆 通不过
    public static int maxProfit(int[] prices) {
        Map<String, Integer> remember = new HashMap<>();
        return buy(prices, 0, remember);
    }

    static int buy(int[] prices, int i, Map<String, Integer> remember) {
        if (i == prices.length - 1)
            return 0;
        int a;
        String keyA = (i + 1) + "buy";
        if (remember.containsKey(keyA)) {
            a = remember.get(keyA);
        } else {
            a = buy(prices, i + 1, remember);
            remember.put(keyA, a);
        }
        return Math.max(a, sell(prices, i + 1, prices[i], remember));
    }

    static int sell(int[] prices, int i, int buyPrice, Map<String, Integer> remember) {
        if (i == prices.length - 1)
            return prices[i] - buyPrice;
        int a;
        String keyA = (i + 1) + "buy";
        if (remember.containsKey(keyA)) {
            a = remember.get(keyA);
        } else {
            a = buy(prices, i + 1, remember);
            remember.put(keyA, a);
        }
        return Math.max(prices[i] - buyPrice + a, sell(prices, i + 1, buyPrice, remember));
    }

    //特殊法
    public static int maxProfit2(int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - prices[i - 1] > 0) {
                result += prices[i] - prices[i - 1];
            }
        }
        return result;
    }
}
