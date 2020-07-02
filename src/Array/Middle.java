package Array;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Middle {

    //下一个排列 https://leetcode-cn.com/problems/next-permutation/
    public static void nextPermutation(int[] nums) {
        int len = nums.length;
        int i = len - 1;
        for (; i >= 1; i--) {
            if (nums[i] > nums[i - 1])
                break;
        }
        if (i == 0) {
            Arrays.sort(nums);
            return;
        }
        int minIndex = i;
        for (int j = len - 1; j >= i; j--) {
            if (nums[j] > nums[i - 1] && nums[j] < nums[minIndex])
                minIndex = j;
        }
        int tmp = nums[i - 1];
        nums[i - 1] = nums[minIndex];
        nums[minIndex] = tmp;
        Arrays.sort(nums, i, len);
    }

    //排序数组 https://leetcode-cn.com/problems/sort-an-array/
    public static int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    public static void quickSort(int[] nums, int start, int end) {
        if (start >= end) return;
        int middle = partition(nums, start, end);
        quickSort(nums, start, middle - 1);
        quickSort(nums, middle + 1, end);
    }

    public static int partition(int[] nums, int start, int end) {
        int pivot = nums[end];
        int j = start - 1;
        for (int i = start; i < end; i++) {
            if (nums[i] <= pivot) {
                j++;
                swap(nums, i, j);
            }
        }
        swap(nums, j + 1, end);
        return j + 1;
    }

    public static void heapSort(int[] nums) {
        initHeap(nums);
        for (int i = 0; i < nums.length; i++) {
            int maxIndex = nums.length - 1 - i;
            swap(nums, 0, maxIndex);
            heapFixDown(nums, 0, maxIndex - 1);
        }
    }

    public static void initHeap(int[] nums) {
        int init = (nums.length - 2) / 2;
        for (int i = init; i >= 0; i--) {
            heapFixDown(nums, i, nums.length - 1);
        }
    }

    public static void heapFixDown(int[] nums, int start, int end) {
        int father = start;
        int child1 = (start + 1) * 2 - 1;
        int child2 = (start + 1) * 2;
        while (father <= end) {
            int max = father;
            if (child1 <= end && nums[child1] > nums[max]) max = child1;
            if (child2 <= end && nums[child2] > nums[max]) max = child2;
            if (max == father) break;
            swap(nums, father, max);
            father = max;
            child1 = (father + 1) * 2 - 1;
            child2 = (father + 1) * 2;
        }
    }

    public static void swap(int[] nums, int i, int j) {
        if (i < 0 || j < 0) return;
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    // 搜索旋转排序数组 https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
    public static int search(int[] nums, int target) {
        return searchHelper(nums, target, 0, nums.length - 1, false);
    }

    public static int searchHelper(int[] nums, int target, int l, int r, boolean isSorted) {
        if (l > r) return -1;
        if (l == r) {
            if (nums[l] == target) return l;
            else return -1;
        }
        int a, b;
        int middle = (l + r) / 2;
        if (isSorted) {
            a = searchHelper(nums, target, l, middle, true);
            b = searchHelper(nums, target, middle + 1, r, true);
        } else {
            if (nums[l] < nums[middle]) {
                a = searchHelper(nums, target, l, middle, true);
                b = searchHelper(nums, target, middle + 1, r, false);
            } else {
                a = searchHelper(nums, target, l, middle, false);
                b = searchHelper(nums, target, middle + 1, r, true);
            }
        }
        return a == -1 ? b : a;
    }

    // 215. 数组中的第K个最大元素 https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
    public static int findKthLargest(int[] nums, int k) {
        int index = helperFor215(nums, k, 0, nums.length - 1);
        return nums[index];
    }

    public static int helperFor215(int[] nums, int k, int l, int r) {
        int middle = partitionFor215(nums, l, r);
        if (middle == -1) return middle;
        if (middle == k - 1) return middle;
        else if (middle > k - 1) {
            return helperFor215(nums, k, l, middle - 1);
        } else {
            return helperFor215(nums, k, middle + 1, r);
        }
    }

    public static int partitionFor215(int[] nums, int l, int r) {
        if (l > r) return -1;
        if (l == r) return l;
        int pivot = nums[r];
        int i = l - 1, j = l;
        for (; j <= r; j++) {
            if (nums[j] > pivot) {
                i++;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, r);
        return i + 1;
    }

    //41. 缺失的第一个正数 https://leetcode-cn.com/problems/first-missing-positive/
    public static int firstMissingPositive(int[] nums) {
        if (nums.length == 0) return 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) nums[i] = nums.length + 1;
        }
        for (int i = 0; i < nums.length; i++) {
            int tmp = Math.abs(nums[i]);
            if (tmp - 1 < 0 || tmp - 1 > nums.length - 1) continue;
            if (nums[tmp - 1] > 0) {
                nums[tmp - 1] = -nums[tmp - 1];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) return i + 1;
        }
        return nums.length + 1;

    }

    //378. 有序矩阵中第K小的元素 https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/
    public static int kthSmallest(int[][] matrix, int k) {

    }

    public static int binarySearchFor378(int[][] matrix, int k) {
        int root = (int) Math.pow(k, 0.5);
        int middle = matrix[root][root];
        int i = k, j = 0;
        while (i >= 0 && j <= k) {
            if (matrix[i][j] <= middle) j++;
            else i--;
        }
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 0};

        System.out.println(firstMissingPositive(nums));
    }

}
