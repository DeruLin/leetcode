package simple;

public class DynamicProgram {

    public static void main(String[] args) {
        System.out.println(climbStairs(4));
    }

    public static int climbStairs(int n) {
        if (n == 1) return 1;
        else if (n == 2) return 2;
        else {
            int pre = 1, now = 2, next = 0;
            int count = 3;
            while (count <= n) {
                next = pre + now;
                pre = now;
                now = next;
                count++;
            }
            return next;
        }
    }
}
