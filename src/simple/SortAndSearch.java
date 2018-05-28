package simple;

import java.lang.reflect.Array;
import java.util.Arrays;

public class SortAndSearch {

    public static void main(String[] args) {
        System.out.println(firstBadVersion(2126753390));
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, count = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) {
                nums1[count--] = nums1[i--];
            } else {
                nums1[count--] = nums2[j--];
            }
        }
        for (; i >= 0; i--)
            nums1[count--] = nums1[i];

        for (; j >= 0; j--)
            nums1[count--] = nums2[j];

    }

    public static int firstBadVersion(int n) {
        return search(1, n);
    }

    private static int search(int start, int end) {
        if (end - start <= 1) {
            if (isBadVersion(start)) return start;
            if (isBadVersion(end)) return end;
            return -1;
        }
        long x=start;
        long y=end;
        long sum = x+y;
        int middle = (int) (sum / 2);
        if (isBadVersion(middle)) {
            int result = search(start, middle - 1);
            return result > 0 ? result : middle;
        } else {
            return search(middle + 1, end);
        }
    }

    private static boolean isBadVersion(int version) {
        return version >= 1702766719;
    }
}
