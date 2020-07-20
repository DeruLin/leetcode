import java.util.*;

public class ALi {

    public static void main(String[] args) {
        int[][] dp = new int[1000][11];
        System.out.println(run(10, 2, 1, 1, new int[][]{{6, 3, 2, 50}, {8, 2, 1, 10}}, dp, 0));
    }

    public static int run(int n, int m, int c0, int d0, int[][] raw, int[][] dp, int index) {
        if (dp[n][index] != 0) return dp[n][index];
        if (index == m) return n / c0 * d0;
        int max = Math.min(raw[index][0] / raw[index][1], n / raw[index][2]);
        int maxValue = 0;
        raw[index][0] += raw[index][1];
        for (int i = 0; i <= max; i++) {
            raw[index][0] -= raw[index][1];
            int tmpValue = i * raw[index][3] + run(n - i * raw[index][2], m, c0, d0, raw, dp, index + 1);
            if (maxValue < tmpValue) maxValue = tmpValue;
        }
        raw[index][0] += max * raw[index][1];
        dp[n][index] = maxValue;
        return maxValue;
    }

}
