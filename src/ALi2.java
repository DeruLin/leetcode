import java.util.*;

public class ALi2 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int n = sc.nextInt();
        int[] machines = new int[n];
        int[] products = new int[n];
        for (int i = 0; i < n; i++) {
            machines[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            products[i] = sc.nextInt();
        }
        int p = sc.nextInt();
        Arrays.sort(machines);
        Arrays.sort(products);
        long result = 1;
        for (int i = 0; i < n; i++) {
            int j = 0;
            while (j < n && machines[i] >= products[j]) {
                j++;
            }
            result *= (j - i);
        }
        System.out.println(result % p);
    }
}

