package Array;


import java.io.RandomAccessFile;
import java.util.*;

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
        return binarySearchFor378(matrix, k, matrix[0][0], matrix[matrix.length - 1][matrix.length - 1]);
    }

    public static int binarySearchFor378(int[][] matrix, int k, int min, int max) {
        if (min >= max) return min;
        int mid = (min + max) / 2;

        int n = matrix.length - 1;
        int i = n, j = 0, count = 0;
        while (i >= 0 && j <= n) {
            if (matrix[i][j] <= mid) {
                count += i + 1;
                j++;
            } else {
                i--;
            }
        }
        if (count >= k)
            return binarySearchFor378(matrix, k, min, mid);

        else
            return binarySearchFor378(matrix, k, mid + 1, max);
    }

//    public static int kthSmallest(int[][] matrix, int k) {
//        int n = matrix.length;
//        int left = matrix[0][0];
//        int right = matrix[n - 1][n - 1];
//        while (left < right) {
//            int mid = left + ((right - left) >> 1);
//            System.out.println("min:" + left + " max:" + right + " mid:" + mid + " check:" + check(matrix, mid, k, n));
//
//            if (check(matrix, mid, k, n)) {
//                right = mid;
//            } else {
//                left = mid + 1;
//            }
//        }
//        return left;
//    }

    public static boolean check(int[][] matrix, int mid, int k, int n) {
        int i = n - 1;
        int j = 0;
        int num = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                num += i + 1;
                j++;
            } else {
                i--;
            }
        }
        return num >= k;
    }

    //15. 三数之和 https://leetcode-cn.com/problems/3sum/
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            if (nums[i] > 0) break;
            List<List<Integer>> tmpResult = twoSum(nums, i);
            for (List<Integer> list : tmpResult) {
                list.add(nums[i]);
            }
            result.addAll(tmpResult);
        }
        return result;

    }

    public static List<List<Integer>> twoSum(int[] nums, int fixIndex) {
        List<List<Integer>> result = new ArrayList<>();
        int i = fixIndex + 1, j = nums.length - 1;
        int target = -nums[fixIndex];
        while (i < j) {
            List<Integer> tmp = new ArrayList<>();
            int currSum = nums[i] + nums[j];
            if (currSum == target) {
                tmp.add(nums[i]);
                tmp.add(nums[j]);
                result.add(tmp);
                while (i + 1 < j && nums[i + 1] == nums[i]) i++;
                i++;
                j--;
            } else if (currSum > target) {
                j--;
            } else {
                i++;
            }
        }
        return result;
    }

    //56. 合并区间 https://leetcode-cn.com/problems/merge-intervals/
    class Range implements Comparable<Range> {
        int min;
        int max;

        Range(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public int compareTo(Range o) {
            if (min < o.min) return -1;
            else if (min > o.min) return 1;
            else {
                return Integer.compare(max, o.max);
            }
        }
    }

    public int[][] merge(int[][] intervals) {
        List<Range> rangeList = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            rangeList.add(new Range(intervals[i][0], intervals[i][1]));
        }
        Collections.sort(rangeList);
        int intervalCount = intervals.length;
        for (int i = 1; i < rangeList.size(); i++) {
            Range prevRange = rangeList.get(i - 1);
            Range currRange = rangeList.get(i);
            if (currRange.min >= prevRange.min && currRange.max <= prevRange.max) {
                currRange.min = prevRange.min;
                currRange.max = prevRange.max;
                prevRange.min = Integer.MAX_VALUE;
                intervalCount--;
            } else if (currRange.min <= prevRange.max) {
                currRange.min = prevRange.min;
                prevRange.min = Integer.MAX_VALUE;
                intervalCount--;
            }
        }
        int[][] result = new int[intervalCount][2];
        int count = 0;
        for (Range range : rangeList) {
            if (range.min == Integer.MAX_VALUE) continue;
            result[count][0] = range.min;
            result[count][1] = range.max;
            count++;
        }
        return result;
    }

    //560. 和为K的子数组 https://leetcode-cn.com/problems/subarray-sum-equals-k/
    public static int subarraySum(int[] nums, int k) {
        int n = nums.length;
        // map：前缀和 -> 该前缀和出现的次数
        HashMap<Integer, Integer>
                preSum = new HashMap<>();
        // base case
        preSum.put(0, 1);

        int ans = 0, sum0_i = 0;
        for (int i = 0; i < n; i++) {
            sum0_i += nums[i];
            // 这是我们想找的前缀和 nums[0..j]
            int sum0_j = sum0_i - k;
            // 如果前面有这个前缀和，则直接更新答案
            if (preSum.containsKey(sum0_j))
                ans += preSum.get(sum0_j);
            // 把前缀和 nums[0..i] 加入并记录出现次数
            preSum.put(sum0_i, preSum.getOrDefault(sum0_i, 0) + 1);
        }
        return ans;
    }

    //498. 对角线遍历 https://leetcode-cn.com/problems/diagonal-traverse/
    public static int[] findDiagonalOrder(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return new int[]{};
        int n = matrix[0].length;
        int[] result = new int[m * n];
        int sum = 0;
        int count = 0;
        boolean flag = false;
        while (sum <= m + n - 2) {
            for (int i = 0; i <= sum; i++) {
                if (flag) {
                    if (sum - i >= n || i >= m) continue;
                    result[count] = matrix[i][sum - i];
                } else {
                    if (sum - i >= m || i >= n) continue;
                    result[count] = matrix[sum - i][i];
                }
                count++;
            }
            flag = !flag;
            sum++;
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{
                {1, 2, 3},
        };
        System.out.println(Arrays.toString(findDiagonalOrder(nums)));
    }

}
