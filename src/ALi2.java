import Tree.Middle;

import java.util.*;

public class ALi2 {

    private static class TreeNode {
        int val;
        List<TreeNode> childs;
        TreeNode parent;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] input = new int[n][2];
        for (int i = 0; i < n; i++) {
            input[i][0] = sc.nextInt();
            input[i][1] = sc.nextInt();
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            queue.add(input[i]);
            while (!queue.isEmpty() && queue.peek()[1] <= input[i][0])
                queue.poll();
            result = Math.max(result, queue.size());
        }
        System.out.println(result);
    }


}
