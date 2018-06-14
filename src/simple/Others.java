package simple;

public class Others {

    public static void main(String[] args) {
        System.out.println(hammingWeight(2147483648));
    }

    public static int hammingWeight(int n) {
        long x = n & 0x0FFFFFFFFl;
        if (x < 1) return 0;
        else if (x <= 2) return 1;
        else {
            int count = 0;
            do {
                long i = x % 2;
                x /= 2;
                if (i == 1) count++;
            } while (x > 0);
            return count;
        }
    }
}
