package simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Others {

    public static void main(String[] args) {
        System.out.println(isValid("("));
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

    public static int reverseBits(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count = (count << 1) + (n & 1);
            n = n >>> 1;
        }
        return count;
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows == 0) return result;

        List<Integer> first = new ArrayList<>();
        first.add(1);
        result.add(first);

        if (numRows == 1) return result;

        List<Integer> second = new ArrayList<>();
        second.add(1);
        second.add(1);
        result.add(second);

        if (numRows == 2) return result;

        List<Integer> preLine = second;
        for (int i = 3; i <= numRows; i++) {
            List<Integer> line = new ArrayList<>();
            line.add(1);
            for (int j = 0; j < preLine.size() - 1; j++) {
                line.add(preLine.get(j) + preLine.get(j + 1));
            }
            line.add(1);
            result.add(line);
            preLine = line;
        }

        return result;
    }

    public static boolean isValid(String s) {
        int len = s.length();
        if (len == 0) return true;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') stack.push(c);
            else if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') return false;
            }
            else if (c == ']') {
                if (stack.isEmpty() || stack.pop() != '[') return false;
            }
            else if (c == '}') {
                if (stack.isEmpty() || stack.pop() != '{') return false;
            }
        }
        return stack.isEmpty();
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
