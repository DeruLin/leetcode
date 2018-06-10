package simple;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Math {

    public static void main(String[] args) {
        fizzBuzz(15);
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
}
