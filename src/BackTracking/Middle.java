package BackTracking;


import java.util.*;

public class Middle {

    //全排列 https://leetcode-cn.com/problems/permutations/
    public static List<List<Integer>> permute(int[] nums) {
        Set<Integer> unused = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            unused.add(i);
        }
        return helper(nums, unused);
    }

    public static List<List<Integer>> helper(int[] nums, Set<Integer> unused) {
        List<List<Integer>> result = new ArrayList<>();
        if (unused.size() == 1) {
            int i = (int) unused.toArray()[0];
            List<Integer> tmp = new ArrayList<>();
            tmp.add(nums[i]);
            result.add(tmp);
            return result;
        }
        for (int i = 0; i < nums.length; i++) {
            if(unused.contains(i)){
                unused.remove(i);
                List<List<Integer>> childResult = helper(nums, unused);
                for (List<Integer> list : childResult) {
                    list.add(0, nums[i]);
                    result.add(list);
                }
                unused.add(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(permute(nums));
    }

}
