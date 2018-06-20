package simple;

public class Others {

    public static void main(String[] args) {
        int a[] = new int[]{0};
        System.out.println(missingNumber(a));
    }

    public static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            int i = n & 1;
            if (i == 1) count++;
            n = n >>> 1;
        }
        return count;
    }

    public static int hammingDistance(int x, int y) {
        int z = x ^ y;
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += (z & 1);
            z = z >>> 1;
        }
        return count;
    }

    public static int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 1; i < n + 1; i++) {
            sum += i;
        }
        for (int i : nums) {
            sum -= i;
        }
        return sum;
    }
}
