package simple;

public class DynamicProgram {

    public static void main(String[] args) {
        int nums[] = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
    }

    public static int climbStairs(int n) {
        if (n == 1) return 1;
        else if (n == 2) return 2;
        else {
            int pre = 1, now = 2, next = 0;
            int count = 3;
            while (count <= n) {
                next = pre + now;
                pre = now;
                now = next;
                count++;
            }
            return next;
        }
    }

    public static int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int profit = 0, min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            int price = prices[i];
            if (price > min) {
                if (profit < price - min) profit = price - min;
            } else {
                min = price;
            }
        }
        return profit;
    }

    public static int maxSubArray(int[] nums) {
        if (nums.length == 0) return 0;
        int sum = nums[0], all_sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (all_sum <= 0) all_sum = num;
            else all_sum += num;
            sum = Math.max(sum, all_sum);
        }
        return sum;
    }
}
