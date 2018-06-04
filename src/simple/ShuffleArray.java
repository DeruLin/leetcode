package simple;

import java.util.Arrays;
import java.util.Random;

public class ShuffleArray {

    private int nums[];

    private int original[];

    public ShuffleArray(int[] nums) {
        this.nums = Arrays.copyOf(nums, nums.length);
        this.original = Arrays.copyOf(nums, nums.length);
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        nums = Arrays.copyOf(original, original.length);
        return original;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        nums = Arrays.copyOf(original, original.length);
        int len = nums.length;
        int result[] = new int[len];
        Random rand = new Random();
        for (int i = 0; i < len; i++) {
            int index = rand.nextInt(len - i);
            result[i] = nums[index];
            nums[index] = nums[len - i - 1];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] list = {0, -12893, 128384};
        ShuffleArray s = new ShuffleArray(list);
        int[] result = s.shuffle();
        for (int i = 0; i < result.length; i++) {
            System.out.print("[" + result[i] + "] ");
        }
        System.out.println();
        result = s.shuffle();
        for (int i = 0; i < result.length; i++) {
            System.out.print("[" + result[i] + "] ");
        }

        System.out.println();
        result = s.shuffle();
        for (int i = 0; i < result.length; i++) {
            System.out.print("[" + result[i] + "] ");
        }

        System.out.println();
        result = s.shuffle();
        for (int i = 0; i < result.length; i++) {
            System.out.print("[" + result[i] + "] ");
        }
    }


}
