import java.util.Scanner;

public class ALi2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println(run(sc.nextInt()));
        }
    }

    public static long run(int x) {
        int y = Integer.MAX_VALUE ^ x;
        int count = 32;
        while (y != 0) {
            y = y & (y - 1);
            count--;
        }
        return (long) Math.pow(2, count);
    }

}
