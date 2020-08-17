import java.util.*;

public class ALi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int d = sc.nextInt();
        int[] nums = new int[n * n];
        for (int i = 0; i < n * n; i++) {
            nums[i] = sc.nextInt();
        }
        System.out.println(isPossible(nums, d));
    }

    public static int isPossible(int[] nums, int d) {
        // write code here
        if (d == 1) {
            int tmpSum = 0;
            for (int i = 1; i < nums.length; i++) {
                tmpSum += nums[i];
            }
            int afterSum = nums[nums.length - 1] * nums.length;
            return afterSum - tmpSum;
        }
        Arrays.sort(nums);
        int sum = nums[0];
        int[] diff = new int[nums.length - 1];
        for (int i = 1; i < nums.length; i++) {
            diff[i - 1] = nums[i] - nums[i - 1];
            sum += nums[i];
        }
        for (int i = 0; i < diff.length; i++) {
            if (d > diff[i] || (diff[i] % d != 0)) return -1;
        }
        int afterSum = nums[nums.length - 1] * nums.length;
        return (afterSum - sum) / d;
    }
}

