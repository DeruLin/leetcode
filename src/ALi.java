import java.util.*;

public class ALi {
    public static void main(String[] args) {
        System.out.println(missingNumber(new int[]{3,0,1}));
    }

    public static int missingNumber (int[] nums) {
        // write code here
        int[] map = new int[nums.length+1];
        for(int i=0;i<nums.length;i++){
            map[nums[i]] = 1;
        }
        for(int i=0;i<nums.length+1;i++){
            if(map[i] != 1){
                return i;
            }
        }
        return 0;
    }
}

