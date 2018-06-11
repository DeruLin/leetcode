package simple;

import java.util.ArrayList;
import java.util.List;

public class MathProblem {

    public static void main(String[] args) {
        countPrimes(10000);
    }

    public static List<String> fizzBuzz(int n) {
        int array[] = new int[n + 1];
        int count = 1;
        while (3 * count <= n) {
            array[3 * count] = 1;
            count++;
        }
        count = 1;
        while (5 * count <= n) {
            int i = 5 * count;
            if (array[i] == 1)
                array[i] = 3;
            else
                array[i] = 2;
            count++;
        }
        List<String> stringList = new ArrayList<>();
        for (int i = 1; i < n + 1; i++) {
            if (array[i] == 0) stringList.add(String.valueOf(i));
            else if (array[i] == 1) stringList.add("Fizz");
            else if (array[i] == 2) stringList.add("Buzz");
            else if (array[i] == 3) stringList.add("FizzBuzz");
        }
        return stringList;
    }

    public static int countPrimes(int n) {
        boolean array[] = new boolean[n];
        for (int i = 2; i < n / 2 + 1; i++) {
            if (array[i]) continue;
            int count = 2;
            while (i * count < n) {
                array[i * count] = true;
                count++;
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!array[i]) {
                count++;
                System.out.println(i);
            }

        }
        return count;
    }
}
