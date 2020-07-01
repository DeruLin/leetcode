package BackTracking;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Diff {

    //51. N皇后 https://leetcode-cn.com/problems/n-queens/
    public static List<List<String>> solveNQueens(int n) {
        String[][] board = new String[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = ".";
            }
        }
        List<List<String>> result = new ArrayList<>();
        helper(n, board, 0, result);
        return result;
    }

    public static void helper(int n, String[][] board, int row, List<List<String>> result) {
        if (row >= n) {
            List<String> tmp = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    sb.append(board[i][j]);
                }
                tmp.add(sb.toString());
            }
            result.add(tmp);
        } else {
            for (int i = row; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (isValid(board, i, j)) {
                        board[i][j] = "Q";
                        helper(n, board, row + 1, result);
                        board[i][j] = ".";
                    }
                }
            }
        }

    }

    public static boolean isValid(String[][] board, int i, int j) {
        for (int k = 0; k < board.length; k++) {
            if (board[i][k].equals("Q")) return false;
            if (board[k][j].equals("Q")) return false;
            for (int l = 0; l < board.length; l++) {
                if (Math.abs(k - i) == Math.abs(l - j) && board[k][l].equals("Q")) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(solveNQueens(4));
    }

}
