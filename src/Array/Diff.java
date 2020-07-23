package Array;

import java.util.*;

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

    //84. 柱状图中最大的矩形 https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> leftStack = new Stack<>();
        Stack<Integer> rightStack = new Stack<>();
        Stack<Integer> leftIndexStack = new Stack<>();
        Stack<Integer> rightIndexStack = new Stack<>();
        int[] leftRange = new int[heights.length];
        int[] rightRange = new int[heights.length];
        for (int l = 0; l < heights.length; l++) {
            int r = heights.length - 1 - l;
            while (!leftStack.isEmpty() && heights[l] < leftStack.peek()) {
                leftStack.pop();
                int index = leftIndexStack.pop();
                leftRange[index] = l - 1 - index;
            }
            leftStack.push(heights[l]);
            leftIndexStack.push(l);

            while (!rightStack.isEmpty() && heights[r] < rightStack.peek()) {
                rightStack.pop();
                int index = rightIndexStack.pop();
                rightRange[index] = index - (r + 1);
            }
            rightStack.push(heights[r]);
            rightIndexStack.push(r);
        }
        while (!leftStack.isEmpty()) {
            leftStack.pop();
            int index = leftIndexStack.pop();
            leftRange[index] = heights.length - index - 1;
        }
        while (!rightStack.isEmpty()) {
            rightStack.pop();
            int index = rightIndexStack.pop();
            rightRange[index] = index;
        }
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int l = leftRange[i];
            int r = rightRange[i];
            maxArea = Math.max(maxArea, heights[i] * (l + r + 1));
        }
        return maxArea;
    }

    //42. 接雨水 https://leetcode-cn.com/problems/trapping-rain-water/
    public static int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> indexStack = new Stack<>();
        int maxHeight = 0, sum = 0, tmpSum = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && stack.peek() <= height[i]) {
                int tmpHeight = stack.pop();
                indexStack.pop();
                if (maxHeight == tmpHeight) {
                    maxHeight = 0;
                    sum += tmpSum;
                    tmpSum = 0;
                }
            }
            stack.push(height[i]);
            indexStack.push(i);
            maxHeight = maxHeight == 0 ? height[i] : maxHeight;
            tmpSum += maxHeight - height[i];
        }
        int index = height.length;
        while (!indexStack.isEmpty()) {
            index = indexStack.pop();
        }
        stack.clear();
        maxHeight = 0;
        tmpSum = 0;
        for (int i = height.length - 1; i >= index; i--) {
            while (!stack.isEmpty() && stack.peek() <= height[i]) {
                int tmpHeight = stack.pop();
                if (maxHeight == tmpHeight) {
                    maxHeight = 0;
                    sum += tmpSum;
                    tmpSum = 0;
                }
            }
            stack.push(height[i]);
            maxHeight = maxHeight == 0 ? height[i] : maxHeight;
            tmpSum += maxHeight - height[i];
        }

        return sum;
    }


    public static void main(String[] args) {
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

}
