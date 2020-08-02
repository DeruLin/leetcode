package Heap;


import java.util.*;

public class Diff {

    static class Project {
        public Integer profit;
        public Integer capital;
    }

    public static int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        Queue<Project> profitQueue = new PriorityQueue<>((o1, o2) -> -o1.profit.compareTo(o2.profit));
        Queue<Project> capitalQueue = new PriorityQueue<>((o1, o2) -> o1.capital.compareTo(o2.capital));
        for (int i = 0; i < Profits.length; i++) {
            Project project = new Project();
            project.profit = Profits[i];
            project.capital = Capital[i];
            capitalQueue.offer(project);
        }
        int count = 1;
        while (count <= k) {
            while (!capitalQueue.isEmpty() && W >= capitalQueue.peek().capital) {
                profitQueue.offer(capitalQueue.poll());
            }
            if (profitQueue.isEmpty()) break;
            else {
                Project project = profitQueue.poll();
                W += project.profit;
                count++;
            }
        }
        return W;

    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(findMaximizedCapital(1, 0, new int[]{1, 2, 3}, new int[]{1, 1, 2}));
    }

}
