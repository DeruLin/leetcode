package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Diff {

    //315. 计算右侧小于当前元素的个数 https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/
    public static List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            result.add(0);
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            int count = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) count++;
            }
            result.set(i, count);
        }
        return result;

    }


    public static void main(String[] args) {
        System.out.println(countSmaller(new int[]{5, 2, 6,1}));
    }

}
