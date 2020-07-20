package Array;

import simple.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

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

    //88. 合并两个有序数组 https://leetcode-cn.com/problems/merge-sorted-array/
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = m - 1; i >= 0; i--) {
            nums1[i + n] = nums1[i];
        }
        int i = n, j = 0, count = 0;
        while (i < n + m && j < n) {
            if (nums1[i] < nums2[j]) {
                nums1[count] = nums1[i];
                i++;
            } else {
                nums1[count] = nums2[j];
                j++;
            }
            count++;
        }
        while (i < n + m) {
            nums1[count] = nums1[i];
            i++;
            count++;
        }
        while (j < n) {
            nums1[count] = nums2[j];
            j++;
            count++;
        }
    }

    //剑指 Offer 61. 扑克牌中的顺子 https://leetcode-cn.com/problems/bu-ke-pai-zhong-de-shun-zi-lcof/
    public static boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int zeroCount = 0, gapCount = 0;
        int i = 0;
        for (; i < nums.length; i++) {
            if (nums[i] == 0) zeroCount++;
            else break;
        }
        int prev = nums[i];
        i++;
        for (; i < nums.length; i++) {
            int gap = nums[i] - prev - 1;
            if (gap < 0) return false;
            gapCount += gap;
            prev = nums[i];
        }
        return zeroCount >= gapCount;
    }


    //单调栈
    public static List<Integer> dailyTemperatures(int[] temp) {
        Stack<Integer> s = new Stack<>();
        Stack<Integer> s_index = new Stack<>();
        Stack<Integer> result = new Stack<>();
        for (int i = temp.length - 1; i >= 0; i--) {
            while (!s.isEmpty() && s.peek() < temp[i]) {
                s.pop();
                s_index.pop();
            }
            if (s.isEmpty())
                result.push(0);
            else {
                result.push(s_index.peek() - i);
            }
            s.push(temp[i]);
            s_index.push(i);
        }
        List<Integer> aa = new ArrayList<>();
        while (!result.isEmpty()){
            aa.add(result.pop());
        }
        return aa;
    }


    public static void main(String[] args) {
        System.out.println(dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73}));

    }

}
