package Bracket;

public class Middle {

    //678. 有效的括号字符串 https://leetcode-cn.com/problems/valid-parenthesis-string/
    public boolean checkValidString(String s) {
        int leftLow = 0;
        int leftHigh = 0;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c == '('){
                leftLow++;
                leftHigh++;
            }else if(c == ')'){
                if(leftLow>0) leftLow--;
                leftHigh--;
                if(leftHigh<0) return false;
            }else{
                if(leftLow>0) leftLow--;
                leftHigh++;
            }
        }
        return leftLow==0;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{
                1, 2, 3, 1
        };
        System.out.println();
    }

}
