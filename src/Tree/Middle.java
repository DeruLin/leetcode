package Tree;

import simple.Tree;

import java.lang.reflect.Array;
import java.util.*;

public class Middle {

    //路径总和 II https://leetcode-cn.com/problems/path-sum-ii/
    public static List<List<Integer>> result;
    public static List<Integer> tmp;
    public static int sumVal;

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        result = new ArrayList<>();
        tmp = new ArrayList<>();
        sumVal = sum;
        traverseForPathSum(root, 0);
        return result;
    }

    public static void traverseForPathSum(TreeNode node, int currentSum) {
        if (node != null) {
            tmp.add(node.val);
            currentSum += node.val;
            if (node.left == null && node.right == null) {
                if (currentSum == sumVal) {
                    result.add(new ArrayList<>(tmp));
                }

            } else {
                traverseForPathSum(node.left, currentSum);
                traverseForPathSum(node.right, currentSum);
            }
            tmp.remove(tmp.size() - 1);
        }
    }

    //二叉树的中序遍历 https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
    public static List<Integer> inOrderResult;
    public static Stack<TreeNode> stack;

    public static List<Integer> inorderTraversal(TreeNode root) {
        inOrderResult = new ArrayList<>();
        stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.empty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            inOrderResult.add(curr.val);
            curr = curr.right;
        }
        return inOrderResult;
    }

    public static void traverseForInorderTraversal(TreeNode node) {
        if (node != null) {
            traverseForInorderTraversal(node.left);
            inOrderResult.add(node.val);
            traverseForInorderTraversal(node.right);
        }
    }

    //不同的二叉搜索树 https://leetcode-cn.com/problems/unique-binary-search-trees/
    public static int numTrees(int n) {
        int[] result = new int[n + 1];
        result[0] = 1;
        result[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            for (int j = 0; j < i; j++) {
                result[i] += result[j] * result[i - 1 - j];
            }
        }
        return result[n];
    }

    //不同的二叉搜索树 II https://leetcode-cn.com/problems/unique-binary-search-trees-ii/
    public static List<TreeNode> generateTrees(int n) {
        List<List<TreeNode>> result = new ArrayList<>();
        result.add(Arrays.asList(new TreeNode[]{null}));
        result.add(Arrays.asList(new TreeNode[]{new TreeNode(1)}));
//        TreeNode tmp1 = new TreeNode(1);
//        tmp1.right = new TreeNode(2);
//        TreeNode tmp2 = new TreeNode(2);
//        tmp1.left = new TreeNode(1);
//        result.add(Arrays.asList(new TreeNode[]{tmp1, tmp2}));
        for (int i = 2; i < n + 1; i++) {
            List<TreeNode> tmpList = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                List<TreeNode> tmpLeftList = result.get(j);
                List<TreeNode> tmpRightList = result.get(i - 1 - j);
                for (TreeNode left : tmpLeftList) {
                    for (TreeNode right : tmpRightList) {
                        TreeNode tmpRoot = new TreeNode(j + 1);
                        tmpRoot.left = left;
                        tmpRoot.right = modifyTree(right, j + 1);
                        tmpList.add(tmpRoot);
                    }
                }
            }
            result.add(tmpList);
        }
        return result.get(n);
    }

    public static TreeNode modifyTree(TreeNode root, int diff) {
        if (root != null) {
            TreeNode node = new TreeNode(root.val + diff);
            node.left = modifyTree(root.left, diff);
            node.right = modifyTree(root.right, diff);
            return node;
        }
        return null;
    }

    //验证二叉搜索树 https://leetcode-cn.com/problems/validate-binary-search-tree/
    public static boolean isValidBST = true;

    public boolean isValidBST(TreeNode root) {
        isValidBST = true;
        traverseForIsValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
        return isValidBST;
    }

    public static void traverseForIsValidBST(TreeNode node, long low, long high) {
        if (!isValidBST) return;
        if (node != null) {
            if (node.val <= low || node.val >= high)
                isValidBST = false;
            traverseForIsValidBST(node.left, low, node.val);
            traverseForIsValidBST(node.right, node.val, high);
        }
    }

    //二叉树的锯齿形层次遍历 https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        boolean needReverse = true;
        List<List<Integer>> result = new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        while (!s.isEmpty()) {
            List<Integer> tmpResult = new ArrayList<>();
            List<TreeNode> tmp = new ArrayList<>();
            while (!s.isEmpty()) {
                tmp.add(s.pop());
            }
            for (TreeNode n : tmp) {
                if (n != null) {
                    tmpResult.add(n.val);
                    if (needReverse) {
                        s.push(n.left);
                        s.push(n.right);
                    } else {
                        s.push(n.right);
                        s.push(n.left);
                    }

                }
            }
            needReverse = !needReverse;
            if (!tmpResult.isEmpty())
                result.add(tmpResult);
        }
        return result;
    }

    //从前序与中序遍历序列构造二叉树 https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, 0, inorder, 0, inorder.length - 1);
    }

    public static TreeNode buildTreeHelper(int[] preorder, int preStart, int[] inorder, int inStart, int inEnd) {
        if (inStart == inEnd) return new TreeNode(inorder[inStart]);
        if (inStart > inEnd) return null;
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        int middle = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (rootVal == inorder[i]) {
                middle = i;
                break;
            }
        }
        int a = preStart + 1;
        int b = preStart + 1 + middle - inStart;
        root.left = buildTreeHelper(preorder, preStart + 1, inorder, inStart, middle - 1);
        root.right = buildTreeHelper(preorder, preStart + 1 + middle - inStart, inorder, middle + 1, inEnd);
        return root;
    }

    //从中序与后序遍历序列构造二叉树 https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
    public static TreeNode buildTree1(int[] inorder, int[] postorder) {
        TreeNode a = buildTreeHelper1(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1);
        return a;
    }

    public static TreeNode buildTreeHelper1(int[] postorder, int postStart, int postEnd, int[] inorder, int inStart, int inEnd) {
        if (inStart == inEnd) return new TreeNode(inorder[inStart]);
        if (inStart > inEnd) return null;
        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);
        int middle = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (rootVal == inorder[i]) {
                middle = i;
                break;
            }
        }
        int a = postStart;
        int b = postStart + middle - 1 - inStart;
        int c = postStart + 1 + middle - 1 - inStart;
        int d = postEnd - 1;
        root.left = buildTreeHelper1(postorder, postStart, postStart + middle - 1 - inStart, inorder, inStart, middle - 1);
        root.right = buildTreeHelper1(postorder, postStart + 1 + middle - 1 - inStart, postEnd - 1, inorder, middle + 1, inEnd);
        return root;
    }

    //二叉树展开为链表 https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
    public static TreeNode prev;

    public static void flatten(TreeNode root) {
        prev = null;
        traverseForFlatten(root);
    }

    public static void traverseForFlatten(TreeNode node) {
        if (node != null) {
            traverseForFlatten(node.right);
            traverseForFlatten(node.left);
            node.left = null;
            node.right = prev;
            prev = node;
        }
    }

    //填充每个节点的下一个右侧节点指针 https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
    public static Node connect(Node root) {
        traverseForConnect(root);
        Node tmp = root;
        while (tmp != null) {
            Node tmp1 = tmp;
            while (tmp1.next != null) {
                if(tmp1.right!=null)
                    tmp1.right.next =  tmp1.next.left;
                tmp1 = tmp1.next;
            }
            tmp = tmp.left;
        }
        return root;
    }

    public static void traverseForConnect(Node node) {
        if (node != null) {
            if (node.left != null && node.right != null) {
                node.left.next = node.right;
            }
            traverseForConnect(node.left);
            traverseForConnect(node.right);
        }
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

    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    ;

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        connect(root);
        System.out.println();
    }

}
