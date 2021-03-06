package simple;

import java.util.ArrayList;
import java.util.List;

public class MathProblem {

    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
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

    public static boolean isPowerOfThree(int n) {
        if (n == 1) return true;
        if (n < 3) return false;
        if (n == 3) return true;
        while (n % 3 == 0) {
            n = n / 3;
            if (n == 3) return true;
        }
        return false;
    }

    public static int romanToInt(String s) {
        char chars[] = s.toCharArray();
        int result = 0;
        char pre = '1';
        for (char c : chars) {
            if (pre == 'I' && c == 'V') {
                result += 3;
                pre = '1';
            } else if (pre == 'I' && c == 'X') {
                result += 8;
                pre = '1';
            } else if (pre == 'X' && c == 'L') {
                result += 30;
                pre = '1';
            } else if (pre == 'X' && c == 'C') {
                result += 80;
                pre = '1';
            } else if (pre == 'C' && c == 'D') {
                result += 300;
                pre = '1';
            } else if (pre == 'C' && c == 'M') {
                result += 800;
                pre = '1';
            } else {
                switch (c) {
                    case 'I':
                        result += 1;
                        break;
                    case 'V':
                        result += 5;
                        break;
                    case 'X':
                        result += 10;
                        break;
                    case 'L':
                        result += 50;
                        break;
                    case 'C':
                        result += 100;
                        break;
                    case 'D':
                        result += 500;
                        break;
                    case 'M':
                        result += 1000;
                        break;
                    default:
                        break;
                }
                pre = c;
            }
        }
        return result;

    }
}
