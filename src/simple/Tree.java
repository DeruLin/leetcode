package simple;

public class Tree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
//        root.left = new TreeNode(1);
//        root.right = new TreeNode(15);
//        root.right.left = new TreeNode(6);
//        root.right.right = new TreeNode(20);
        System.out.println(isValidBST(root));
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

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
