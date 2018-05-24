package simple;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Tree {

    public static void main(String[] args) {
        int nums[] = new int[]{-10, -3, 0,2, 5, 9};
        sortedArrayToBST(nums);
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        else if (root.left == null && root.right == null) return 1;
        else {
            int leftDepth = maxDepth(root.left);
            int rightDepth = maxDepth(root.right);
            return leftDepth > rightDepth ? leftDepth + 1 : rightDepth + 1;
        }
    }

    private static long pre = Long.MIN_VALUE;

    public static boolean isValidBST(TreeNode root) {
        return inOrder(root);
    }

    private static boolean inOrder(TreeNode root) {
        if (root == null) return true;
        if (!inOrder(root.left)) return false;
        if (pre >= root.val) return false;
        else {
            pre = root.val;
        }
        return inOrder(root.right);
    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        else if (root.right == null && root.left == null) return true;
        else if (root.left == null || root.right == null) return false;
        else {
            return inOrderTwoTree(root.left, root.right);
        }
    }

    private static boolean inOrderTwoTree(TreeNode left, TreeNode right) {
        if (right == null && left == null) return true;
        else if (left == null || right == null) return false;

        if (!inOrderTwoTree(left.left, right.right)) return false;
        if (left.val != right.val) return false;
        return inOrderTwoTree(left.right, right.left);
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        List<Integer> temp = new ArrayList<>();
        temp.add(root.val);
        result.add(temp);
        List<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        while (queue.size() > 0) {
            List<Integer> list = new ArrayList<>();
            List<TreeNode> tempQueue = new ArrayList<>();
            for (TreeNode node : queue) {
                if (node.left != null) {
                    list.add(node.left.val);
                    tempQueue.add(node.left);
                }
                if (node.right != null) {
                    list.add(node.right.val);
                    tempQueue.add(node.right);
                }
            }
            queue = tempQueue;
            if (!list.isEmpty()) {
                result.add(list);
            }
        }
        return result;

    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }

    private static TreeNode buildTree(int[] nums, int start, int end) {
        if (start > end) return null;
        int middle = (start + end) / 2;
        int i = nums[middle];
        if (start == end) return new TreeNode(i);
        TreeNode root = new TreeNode(i);
        root.left = buildTree(nums, start, middle - 1);
        root.right = buildTree(nums, middle + 1, end);
        return root;
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
