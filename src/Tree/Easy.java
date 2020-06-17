package Tree;

import simple.Tree;

import java.util.*;

public class Easy {

    //二叉树的层平均值 https://leetcode-cn.com/problems/average-of-levels-in-binary-tree/
    public static List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            double sum = 0;
            int count = 0;
            List<TreeNode> tmp = new ArrayList<>();
            while (!q.isEmpty()) {
                tmp.add(q.poll());
            }
            for (TreeNode n : tmp) {
                if (n != null) {
                    sum += n.val;
                    count++;
                    q.offer(n.left);
                    q.offer(n.right);
                }
            }
            result.add(sum / count);
        }
        return result;
    }

    //平衡二叉树 https://leetcode-cn.com/problems/balanced-binary-tree/
    public static boolean isBalanced(TreeNode root) {
        return traverseForIsBalanced(root) != -1;
    }

    public static int traverseForIsBalanced(TreeNode node) {
        if (node == null) return 0;
        int leftHeight = traverseForIsBalanced(node.left);
        int rightHeight = traverseForIsBalanced(node.right);
        if (leftHeight == -1 || rightHeight == -1) return -1;
        else {
            if (Math.abs(rightHeight - leftHeight) > 1) return -1;
            else return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    public static TreeNode aa;

    //BiNode https://leetcode-cn.com/problems/binode-lcci/
    public static TreeNode convertBiNode(TreeNode root) {
        if (root == null) return null;
        aa = new TreeNode(-1);
        TreeNode start = aa;
        traverseForConvertBiNode(root);
        return start.right;
    }

    public static void traverseForConvertBiNode(TreeNode node) {
        if (node != null) {
            traverseForConvertBiNode(node.left);
            node.left = null;
            aa.right = node;
            aa = aa.right;
            traverseForConvertBiNode(node.right);
        }
    }

    //二叉树的所有路径 https://leetcode-cn.com/problems/binary-tree-paths/
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        traverseForBinaryTreePaths(root, "", result);
        return result;
    }

    public static void traverseForBinaryTreePaths(TreeNode node, String path, List<String> result) {
        if (node != null) {
            if (path.isEmpty()) {
                path += node.val;
            } else {
                path = path + "->" + node.val;
            }
            if (node.left == null && node.right == null) result.add(path);
            traverseForBinaryTreePaths(node.left, path, result);
            traverseForBinaryTreePaths(node.right, path, result);
        }
    }

    //二叉树的坡度 https://leetcode-cn.com/problems/binary-tree-tilt/
    public static int totalTilt = 0;

    public static int findTilt(TreeNode root) {
        totalTilt = 0;
        traverseForFindTilt(root);
        return totalTilt;
    }

    public static int traverseForFindTilt(TreeNode node) {
        if (node != null) {
            int leftSum = traverseForFindTilt(node.left);
            int rightSum = traverseForFindTilt(node.right);
            int tmpTilt = Math.abs(leftSum - rightSum);
            totalTilt += tmpTilt;
            return leftSum + node.val + rightSum;
        }
        return 0;
    }

    //根据二叉树创建字符串 https://leetcode-cn.com/problems/construct-string-from-binary-tree/
    public static String tree2str(TreeNode t) {
        if (t != null) {
            String leftStr = tree2str(t.left);
            String rightStr = tree2str(t.right);
            if (leftStr.isEmpty() && rightStr.isEmpty()) {
                return String.valueOf(t.val);
            } else if (rightStr.isEmpty()) {
                return t.val + "(" + leftStr + ")";
            } else {
                return t.val + "(" + leftStr + ")" + "(" + rightStr + ")";
            }
        }
        return "";
    }

    //把二叉搜索树转换为累加树 https://leetcode-cn.com/problems/convert-bst-to-greater-tree/
    public static int previousSum = 0;

    public static TreeNode convertBST(TreeNode root) {
        previousSum = 0;
        traverseForConvertBST(root);
        return root;
    }

    public static void traverseForConvertBST(TreeNode node) {
        if (node != null) {
            traverseForConvertBST(node.right);
            node.val += previousSum;
            previousSum = node.val;
            traverseForConvertBST(node.left);
        }
    }

    //将有序数组转换为二叉搜索树 https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
    public static TreeNode sortedArrayToBST(int[] nums) {
        return BinarySearchForSortedArrayToBST(nums, 0, nums.length - 1);
    }

    public static TreeNode BinarySearchForSortedArrayToBST(int[] nums, int start, int end) {
        TreeNode treeNode = new TreeNode(0);
        if (start < end) {
            int middle = (start + end) / 2;
            treeNode.val = nums[middle];
            if (start == middle) {
                treeNode.left = null;
            } else {
                treeNode.left = BinarySearchForSortedArrayToBST(nums, start, middle - 1);
            }
            treeNode.right = BinarySearchForSortedArrayToBST(nums, middle + 1, end);
            return treeNode;
        } else if (start == end) {
            treeNode.val = nums[start];
            return treeNode;
        } else {
            return null;
        }
    }

    //二叉树的堂兄弟节点 https://leetcode-cn.com/problems/cousins-in-binary-tree/
    public static int levelX = 0, levelY = 0, parentX = 0, parentY = 0, findX = 0, findY = 0;

    public static boolean isCousins(TreeNode root, int x, int y) {
        if (root.val == x || root.val == y) return false;
        levelX = 0;
        levelY = 0;
        parentX = 0;
        parentY = 0;
        findX = x;
        findY = y;
        traverseForIsCousins(root, 0, 0);
        return (parentX != parentY && levelX == levelY);
    }

    public static void traverseForIsCousins(TreeNode node, int parentVal, int level) {
        if (node != null) {
            if (node.val == findX) {
                levelX = level;
                parentX = parentVal;
            }
            if (node.val == findY) {
                levelY = level;
                parentY = parentVal;
            }
            if (levelX != 0 && levelY != 0) return;
            level++;
            traverseForIsCousins(node.left, node.val, level);
            traverseForIsCousins(node.right, node.val, level);
        }
    }

    //二叉树的直径 https://leetcode-cn.com/problems/diameter-of-binary-tree/
    public static int maxLength = 0;

    public static int diameterOfBinaryTree(TreeNode root) {
        maxLength = 0;
        traverseForDiameterOfBinaryTree(root);
        return maxLength;
    }

    public static int traverseForDiameterOfBinaryTree(TreeNode node) {
        if (node != null) {
            int leftLength = traverseForDiameterOfBinaryTree(node.left);
            int rightLength = traverseForDiameterOfBinaryTree(node.right);
            if (leftLength + rightLength > maxLength) maxLength = leftLength + rightLength;
            return rightLength > leftLength ? rightLength + 1 : leftLength + 1;
        }
        return 0;
    }

    //二叉搜索树中的众数 https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/
    public static int maxCount = 0, tmpCount = 0, preVal = 0;

    public static int[] findMode(TreeNode root) {
        maxCount = 1;
        tmpCount = 1;
        preVal = 0;
        if (root == null) return new int[]{};
        else {
            preVal = root.val + 100;
            Set<Integer> result = new HashSet<>();
            traverseForFindMode(root, result);
            Object[] tmp = result.toArray();
            int[] aa = new int[tmp.length];
            for (int i = 0; i < tmp.length; i++) {
                aa[i] = (int) tmp[i];
            }
            return aa;
        }

    }

    public static void traverseForFindMode(TreeNode node, Set<Integer> result) {
        if (node != null) {
            traverseForFindMode(node.left, result);
            if (node.val == preVal) {
                tmpCount++;
                if (tmpCount == maxCount) {
                    result.add(node.val);
                } else if (tmpCount > maxCount) {
                    maxCount = tmpCount;
                    result.clear();
                    result.add(node.val);
                }
            } else {
                if (maxCount == 1) {
                    result.add(node.val);
                }
                preVal = node.val;
                tmpCount = 1;
            }
            traverseForFindMode(node.right, result);
        }
    }

    //递增顺序查找树 https://leetcode-cn.com/problems/increasing-order-search-tree/
    public static TreeNode tmp;

    public static TreeNode increasingBST(TreeNode root) {
        tmp = new TreeNode(0);
        TreeNode result = tmp;
        traverseForIncreasingBST(root);
        return result.right;
    }

    public static void traverseForIncreasingBST(TreeNode node) {
        if (node != null) {
            traverseForIncreasingBST(node.left);
            tmp.right = node;
            tmp.left = null;
            tmp = node;
            traverseForIncreasingBST(node.right);
        }
    }

    // 翻转二叉树 https://leetcode-cn.com/problems/invert-binary-tree/
    public static TreeNode invertTree(TreeNode root) {
        traverseForInvertTree(root);
        return root;
    }

    public static void traverseForInvertTree(TreeNode node) {
        if (node != null) {
            traverseForInvertTree(node.left);
            traverseForInvertTree(node.right);
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
        }
    }

    //叶子相似的树 https://leetcode-cn.com/problems/leaf-similar-trees/

    public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leafValList1 = new ArrayList<>();
        List<Integer> leafValList2 = new ArrayList<>();
        traverseForLeafSimilar(root1, leafValList1);
        traverseForLeafSimilar(root2, leafValList2);
        if (leafValList1.size() != leafValList2.size()) return false;
        for (int i = 0; i < leafValList1.size(); i++) {
            if (!leafValList1.get(i).equals(leafValList2.get(i))) return false;
        }
        return true;
    }

    public static boolean traverseForLeafSimilar(TreeNode node, List<Integer> leafValList) {
        if (node != null) {
            boolean b1 = traverseForLeafSimilar(node.left, leafValList);
            boolean b2 = traverseForLeafSimilar(node.right, leafValList);
            if (b1 && b2) {
                leafValList.add(node.val);
            }
            return false;
        }
        return true;
    }

    //最长同值路径 https://leetcode-cn.com/problems/longest-univalue-path/
    public static int maxLengthForLongestUnivaluePath = 0;

    public static int longestUnivaluePath(TreeNode root) {
        maxLengthForLongestUnivaluePath = 0;
        traverseForLongestUnivaluePath(root, 0);
        return maxLengthForLongestUnivaluePath;
    }

    public static int traverseForLongestUnivaluePath(TreeNode node, int parentVal) {
        if (node != null) {
            int leftLength = traverseForLongestUnivaluePath(node.left, node.val);
            int rightLength = traverseForLongestUnivaluePath(node.right, node.val);
            int returnLength;
            if (leftLength == -1 && rightLength == -1) {
                returnLength = 1;
            } else if (rightLength == -1) {
                if (leftLength > maxLengthForLongestUnivaluePath)
                    maxLengthForLongestUnivaluePath = leftLength;
                returnLength = leftLength + 1;
            } else if (leftLength == -1) {
                if (rightLength > maxLengthForLongestUnivaluePath)
                    maxLengthForLongestUnivaluePath = rightLength;
                returnLength = rightLength + 1;
            } else {
                if (leftLength + rightLength > maxLengthForLongestUnivaluePath)
                    maxLengthForLongestUnivaluePath = leftLength + rightLength;
                returnLength = rightLength > leftLength ? rightLength + 1 : leftLength + 1;
            }
            if (parentVal == node.val) return returnLength;
            else return -1;
        }
        return 0;
    }

    //二叉树的最小深度 https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
    public static int minDepth(TreeNode root) {
        if (root == null) return 0;
        int minDepth = 0;
        boolean flag = false;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            List<TreeNode> tmp = new ArrayList<>();
            while (!q.isEmpty()) {
                tmp.add(q.poll());
            }
            for (TreeNode n : tmp) {
                if (n != null) {
                    if (n.left == null && n.right == null) {
                        flag = true;
                        break;
                    }
                    q.offer(n.left);
                    q.offer(n.right);
                }
            }
            if (flag) break;
            minDepth++;
        }
        return minDepth + 1;
    }

    //二叉树中第二小的节点 https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree/
    public static long secondMinVal = Long.MAX_VALUE;
    public static int minVal;

    public static int findSecondMinimumValue(TreeNode root) {
        if (root == null) return -1;
        minVal = root.val;
        traverseForFindSecondMinimumValue(root);
        return secondMinVal == Long.MAX_VALUE ? -1 : (int) secondMinVal;
    }

    public static void traverseForFindSecondMinimumValue(TreeNode node) {
        if (node != null) {
            if (node.val > minVal) {
                if (node.val < secondMinVal)
                    secondMinVal = node.val;
                return;
            }
            traverseForFindSecondMinimumValue(node.left);
            traverseForFindSecondMinimumValue(node.right);
        }
    }

    //另一个树的子树 https://leetcode-cn.com/problems/subtree-of-another-tree/
    public static boolean isSubTree = false;

    public static boolean isSubtree(TreeNode s, TreeNode t) {
        isSubTree = false;
        traverseForIsSubtree(s, t);
        return isSubTree;
    }

    public static void traverseForIsSubtree(TreeNode node, TreeNode subRoot) {
        if (isSubTree) return;
        if (node != null) {
            traverseForIsSubtree(node.left, subRoot);
            traverseForIsSubtree(node.right, subRoot);
            if (!isSubTree && node.val == subRoot.val) {
                isSubTree = isSame(node, subRoot);
            }
        }
    }

    public static boolean isSame(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        else if (root2 == null) return false;
        else if (root1 == null) return false;
        else {
            return root1.val == root2.val && isSame(root1.left, root2.left) && isSame(root1.right, root2.right);
        }
    }

    //路径总和 https://leetcode-cn.com/problems/path-sum/
    public static boolean hasPathSum = false;
    public static int sumVal;

    public boolean hasPathSum(TreeNode root, int sum) {
        hasPathSum = false;
        sumVal = sum;
        traverseForHasPathSum(root, 0);
        return hasPathSum;
    }

    public static void traverseForHasPathSum(TreeNode node, int currentSum) {
        if (hasPathSum) return;
        if (node != null) {
            currentSum += node.val;
            if (currentSum == sumVal && node.left == null && node.right == null) hasPathSum = true;
            traverseForHasPathSum(node.left, currentSum);
            traverseForHasPathSum(node.right, currentSum);
        }
    }

    //对称二叉树 https://leetcode-cn.com/problems/symmetric-tree/
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return traverseForIsSymmetric(root.left, root.right);

    }

    public static boolean traverseForIsSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (right == null)
            return false;
        else if (left == null)
            return false;
        else {
            if (left.val == right.val) {
                return traverseForIsSymmetric(left.left, right.right) && traverseForIsSymmetric(left.right, right.left);
            } else
                return false;
        }
    }

    //二叉搜索树节点最小距离 https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/
    public static int preValForMinDiffInBST;
    public static int minDiff;
    public static boolean isFirstForMinDiffInBST;

    public static int minDiffInBST(TreeNode root) {
        isFirstForMinDiffInBST = true;
        preValForMinDiffInBST = Integer.MIN_VALUE;
        minDiff = Integer.MAX_VALUE;
        traverseForMinDiffInBST(root);
        return minDiff;
    }

    public static void traverseForMinDiffInBST(TreeNode node) {
        if (node != null) {
            traverseForMinDiffInBST(node.left);
            if (isFirstForMinDiffInBST) {
                preValForMinDiffInBST = node.val;
                isFirstForMinDiffInBST = false;
            } else {
                if (minDiff > Math.abs(node.val - preValForMinDiffInBST))
                    minDiff = Math.abs(node.val - preValForMinDiffInBST);
                preValForMinDiffInBST = node.val;
            }
            traverseForMinDiffInBST(node.right);
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

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        System.out.println(isSymmetric(root));
    }

}
