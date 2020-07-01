package Array;

import java.util.Arrays;

public class Easy {

    //189. 旋转数组 https://leetcode-cn.com/problems/rotate-array/
    public static void rotate(int[] nums, int k) {
        int prev = nums[0];
        int count = 0;
        int i = 0;
        while (count < nums.length) {

            int next = i + k;
            if (next >= nums.length) next = next - nums.length;
            int tmp = nums[next];
            nums[next] = prev;
            prev = tmp;
            i = next;
            count++;
        }
        System.out.println(Arrays.toString(nums));
    }


    public static void main(String[] args) {
        rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
        System.out.println();
    }

}
