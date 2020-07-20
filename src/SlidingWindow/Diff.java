package SlidingWindow;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class Diff {

    static class MonotonousList {

        private Deque<Integer> deque;

        public MonotonousList() {
            deque = new LinkedBlockingDeque<>();
        }

        public void add(int n) {
            while (!deque.isEmpty() && deque.peekLast() < n) {
                deque.removeLast();
            }
            deque.add(n);
        }

        public void del(int n) {
            if(!deque.isEmpty() && deque.peekFirst()==n)
                deque.removeFirst();
        }

        public int max() {
            return deque.getFirst();
        }

    }

    //239. 滑动窗口最大值 https://leetcode-cn.com/problems/sliding-window-maximum/
    public static int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        MonotonousList monotonousList = new MonotonousList();
        for (int i = 0; i < nums.length; i++) {
            if (i < k-1) {
                monotonousList.add(nums[i]);
                continue;
            }
            if (i == k-1) {
                monotonousList.add(nums[i]);
                result.add(monotonousList.max());
                continue;
            }
            monotonousList.add(nums[i]);
            monotonousList.del(nums[i-k]);
            result.add(monotonousList.max());
        }
        int[] tmp = new int[result.size()];
        for (int i=0;i<tmp.length;i++){
            tmp[i]=result.get(i);
        }
        return tmp;
    }

    public static void main(String[] args) {
        System.out.println(maxSlidingWindow(new int[]{3, 2, 6, 5, 0, 3},2));
    }

}
