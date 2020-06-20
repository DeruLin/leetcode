package Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Diff {

    //从先序遍历还原二叉树 https://leetcode-cn.com/problems/recover-a-tree-from-preorder-traversal/
    public static TreeNode recoverFromPreorder(String S) {
        int level = 0;
        String num = "";
        List<Integer> valList = new ArrayList<>();
        List<Integer> levelList = new ArrayList<>();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if ('0' <= c && c <= '9') {
                if (num.isEmpty()) {
                    levelList.add(level);
                    level = 0;
                }
                num += c;
            }
            if (c == '-') {
                if (level == 0) {
                    valList.add(Integer.parseInt(num));
                    num = "";
                }
                level++;
            }
        }
        valList.add(Integer.parseInt(num));
        return helperForRecoverFromPreorder(valList, levelList, 0, valList.size() - 1);

    }

    public static TreeNode helperForRecoverFromPreorder(List<Integer> valList, List<Integer> levelList, int start, int end) {
        if (start > end) return null;
        if (start == end) return new TreeNode(valList.get(start));
        TreeNode root = new TreeNode(valList.get(start));
        int nextLevel = levelList.get(start) + 1;
        int a = -1, b = -1;
        for (int i = start + 1; i <= end; i++) {
            if (levelList.get(i) == nextLevel) {
                if (a == -1) a = i;
                else {
                    b = i;
                    break;
                }
            }
        }
        if (a == -1) {
            root.left = null;
            root.right = null;
        } else if (b == -1) {
            root.left = helperForRecoverFromPreorder(valList, levelList, a, end);
        } else {
            root.left = helperForRecoverFromPreorder(valList, levelList, a, b - 1);
            root.right = helperForRecoverFromPreorder(valList, levelList, b, end);
        }
        return root;
    }

    //二叉树的后序遍历 https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode tmp = stack.pop();
            if (tmp != null) {
                stack.push(tmp);
                stack.push(null);
                if (tmp.right != null) stack.push(tmp.right);
                if (tmp.left != null) stack.push(tmp.left);
            } else {
                result.add(stack.pop().val);
            }
        }
        return result;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
//        root.right.left = new TreeNode(6);
//        root.right.right = new TreeNode(7);

        TreeNode aa = recoverFromPreorder("1-2--3--4-5--6--7");
        System.out.println();
    }

}
