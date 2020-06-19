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
                if (tmp1.right != null)
                    tmp1.right.next = tmp1.next.left;
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

    public static Node connect1(Node root) {
        if (root == null) {
            return root;
        }
        Node curr = root;
        while (curr != null) {
            Node head = curr;
            Node nextLevelHead = null;
            Node nextLevelFirst = null;
            boolean flag = true;
            while (head != null) {
                if (head.left == null && head.right == null) {
                    head = head.next;
                    continue;
                } else if (head.right == null) {
                    if (nextLevelHead == null) {
                        if (flag) {
                            nextLevelFirst = head.left;
                            flag = false;
                        }
                    } else {
                        nextLevelHead.next = head.left;
                    }
                    nextLevelHead = head.left;
                } else if (head.left == null) {
                    if (nextLevelHead == null) {
                        if (flag) {
                            nextLevelFirst = head.right;
                            flag = false;
                        }
                    } else {
                        nextLevelHead.next = head.right;
                    }
                    nextLevelHead = head.right;
                } else {
                    if (nextLevelHead == null) {
                        if (flag) {
                            nextLevelFirst = head.left;
                            flag = false;
                        }
                    } else {
                        nextLevelHead.next = head.left;
                    }
                    head.left.next = head.right;
                    nextLevelHead = head.right;
                }
                head = head.next;
            }
            curr = nextLevelFirst;
        }
        return root;
    }


    //求根到叶子节点数字之和 https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/
    public static int totalSum;

    public static int sumNumbers(TreeNode root) {
        totalSum = 0;
        traverseForSumNumbers(root, 0);
        return totalSum;
    }

    public static void traverseForSumNumbers(TreeNode node, int currSum) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                totalSum += (currSum * 10 + node.val);
            }
            traverseForSumNumbers(node.left, currSum * 10 + node.val);
            traverseForSumNumbers(node.right, currSum * 10 + node.val);
        }
    }

    //二叉树的前序遍历 https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode tmp = stack.pop();
            if (tmp != null) {
                if (tmp.right != null) stack.push(tmp.right);
                if (tmp.left != null) stack.push(tmp.left);
                stack.push(tmp);
                stack.push(null);
            } else {
                result.add(stack.pop().val);
            }
        }
        return result;
    }

    //打家劫舍 III https://leetcode-cn.com/problems/house-robber-iii/
    public static int[] levelSum;

    public static int rob(TreeNode root) {
        levelSum = new int[10000];
        traverseForRob(root, 0);
        List<Integer> dp = new ArrayList<>();
        int index = 0;
        dp.add(levelSum[0]);
        dp.add(Math.max(levelSum[0], levelSum[1]));
        for (int i = 2; i < levelSum.length; i++) {
            if (levelSum[i] == 0) {
                index = i;
                break;
            }
            dp.add(Math.max(dp.get(i - 2) + levelSum[i], dp.get(i - 1)));
        }
        return dp.get(index);
    }

    public static void traverseForRob(TreeNode node, int level) {
        if (node != null) {
            traverseForRob(node.left, level + 1);
            levelSum[level] += node.val;
            traverseForRob(node.right, level + 1);
        }
    }

    //删除二叉搜索树中的节点 https://leetcode-cn.com/problems/delete-node-in-a-bst/
    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root.val == key) {
            // 找到啦，进行删除
            if (root.left == null && root.right == null)
                return null;
            else if (root.right == null)
                return root.left;
            else if (root.left == null)
                return root.right;
            else {
                TreeNode minNode = getMin(root.right);
                root.val = minNode.val;
                root.right = deleteNode(root.right, minNode.val);
            }
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    public static TreeNode getMin(TreeNode node) {
        // BST 最左边的就是最小的
        while (node.left != null) node = node.left;
        return node;
    }

    // 二叉树最大宽度 https://leetcode-cn.com/problems/maximum-width-of-binary-tree/
    public static int[] maxPosition, minPosition;

    public static int widthOfBinaryTree(TreeNode root) {
        maxPosition = new int[10000];
        minPosition = new int[10000];
        if (root == null) return 0;
        traverseForWidthOfBinaryTree(root, 1, 0);
        int width = 0;
        for (int i = 0; i < maxPosition.length; i++) {
            if (maxPosition[i] == 0 || minPosition[i] == 0) break;
            if (maxPosition[i] - minPosition[i] + 1 > width) width = maxPosition[i] - minPosition[i] + 1;
        }
        return width;
    }

    public static void traverseForWidthOfBinaryTree(TreeNode node, int position, int level) {
        if (node != null) {
            if (maxPosition[level] == 0) maxPosition[level] = position;
            else if (maxPosition[level] < position) maxPosition[level] = position;
            if (minPosition[level] == 0) minPosition[level] = position;
            else if (minPosition[level] > position) minPosition[level] = position;
            traverseForWidthOfBinaryTree(node.left, position * 2 - 1, level + 1);
            traverseForWidthOfBinaryTree(node.right, position * 2, level + 1);
        }
    }

    //二叉树的右视图 https://leetcode-cn.com/problems/binary-tree-right-side-view/
    public static List<Integer> res = new ArrayList<>();

    public static List<Integer> rightSideView(TreeNode root) {
        traverseForRightSideView(root, 0); // 从根节点开始访问，根节点深度是0
        return res;
    }

    public static void traverseForRightSideView(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        // 先访问 当前节点，再递归地访问 右子树 和 左子树。
        if (depth == res.size()) {   // 如果当前节点所在深度还没有出现在res里，说明在该深度下当前节点是第一个被访问的节点，因此将当前节点加入res中。
            res.add(root.val);
        }
        depth++;
        traverseForRightSideView(root.right, depth);
        traverseForRightSideView(root.left, depth);
    }

    //完全二叉树的节点个数 https://leetcode-cn.com/problems/count-complete-tree-nodes/
    public static int maxLevel, count;

    public static int countNodes(TreeNode root) {
        maxLevel = -1;
        count = 0;
        traverseForCountNodes(root, 0);
        return (int) (Math.pow(2, maxLevel) - 1 + count);
    }

    public static void traverseForCountNodes(TreeNode root, int level) {
        if (root != null) {
            if (maxLevel == level) count++;
            traverseForCountNodes(root.right, level + 1);
            traverseForCountNodes(root.left, level + 1);
        } else {
            if (maxLevel == -1)
                maxLevel = level;
        }
    }

    //二叉搜索树中第K小的元素 https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/
    public static int countForKthSmallest, val, searchK;
    public static boolean flag;

    public int kthSmallest(TreeNode root, int k) {
        searchK = k;
        countForKthSmallest = 0;
        val = 0;
        flag = false;
        traverseForKthSmallest(root);
        return val;
    }

    public static void traverseForKthSmallest(TreeNode root) {
        if (flag) return;
        if (root != null) {
            traverseForKthSmallest(root.left);
            countForKthSmallest++;
            if (countForKthSmallest == searchK) {
                flag = true;
                val = root.val;
            }
            traverseForKthSmallest(root.right);
        }
    }

    //二叉树的最近公共祖先 https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root.val == p.val || root.val == q.val) return root;
        if (root.left == null && root.right == null) return null;
        TreeNode a = lowestCommonAncestor(root.left, p, q);
        TreeNode b = lowestCommonAncestor(root.right, p, q);
        if (a != null && b != null) {
            return root;
        }
        if (a != null) return a;
        return b;
    }

    //N叉树的层序遍历 https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/
    public List<List<Integer>> levelOrder(MultiNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<MultiNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            List<Integer> levelResult = new ArrayList<>();
            List<MultiNode> tmpList = new ArrayList<>(q);
            q.clear();
            for (MultiNode node : tmpList) {
                if (node != null) {
                    levelResult.add(node.val);
                    q.addAll(node.children);
                }
            }
            result.add(levelResult);
        }
        return result;
    }

    //出现次数最多的子树元素和 https://leetcode-cn.com/problems/most-frequent-subtree-sum/
    public static Map<Integer, Integer> countMap;

    public static int[] findFrequentTreeSum(TreeNode root) {
        countMap = new HashMap<>();
        traverseForFindFrequentTreeSum(root);
        int maxCount = 0;
        List<Integer> maxSumList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == maxCount) {
                maxSumList.add(entry.getKey());
            }
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxSumList.clear();
                maxSumList.add(entry.getKey());
            }
        }
        int[] array = new int[maxSumList.size()];
        for (int i = 0; i < maxSumList.size(); i++) {
            array[i] = maxSumList.get(i);
        }
        return array;
    }

    public static int traverseForFindFrequentTreeSum(TreeNode root) {
        if (root != null) {
            int sum = traverseForFindFrequentTreeSum(root.left) + root.val + traverseForFindFrequentTreeSum(root.right);
            if (countMap.containsKey(sum)) {
                countMap.put(sum, countMap.get(sum) + 1);
            } else
                countMap.put(sum, 1);
            return sum;
        }
        return 0;
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

    class MultiNode {
        public int val;
        public List<MultiNode> children;

        public MultiNode() {
        }

        public MultiNode(int _val) {
            val = _val;
        }

        public MultiNode(int _val, List<MultiNode> _children) {
            val = _val;
            children = _children;
        }
    }

    ;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(-3);
//        root.left.left = new TreeNode(6);
//        root.left.right = new TreeNode(2);
//        root.left.right.left = new TreeNode(7);
//        root.left.right.right = new TreeNode(4);
//        root.right.left = new TreeNode(0);
//        root.right.right = new TreeNode(8);
        System.out.println(findFrequentTreeSum(root));
    }

}
