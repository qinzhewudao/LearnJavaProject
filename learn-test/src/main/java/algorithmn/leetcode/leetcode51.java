package algorithmn.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * created at 2019/7/27
 * n皇后问题
 *
 * @author sheyang
 */
public class leetcode51 {

    public List<List<String>> solveNQueens(int n) {

        //初始化棋盘
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        //当前列是否可用
        boolean[] columns = new boolean[n];

        //左斜线是否可用
        boolean[] obliqueLeft = new boolean[2 * n - 1];

        //右斜线是否可用
        boolean[] obliqueRight = new boolean[2 * n - 1];

        List<List<String>> result = new ArrayList<>();

        backtrack(board, 0, n, result, columns, obliqueLeft, obliqueRight);

        return result;
    }

    private void backtrack(char[][] board, int currentRow, int n, List<List<String>> result,
                           boolean[] columns, boolean[] obliqueLeft, boolean[] obliqueRight) {
        //放置结束
        if (currentRow == n) {
            List<String> current = new ArrayList<>();
            for (char[] line : board) {
                current.add(String.valueOf(line));
            }
            result.add(current);
            return;
        }

        for (int column = 0; column < n; column++) {

            //不可放置
            if (columns[column] || obliqueLeft[currentRow + n - column - 1] || obliqueRight[currentRow + column]) {
                continue;
            }

            //放置棋子
            columns[column] = true;
            obliqueLeft[currentRow + n - column - 1] = true;
            obliqueRight[currentRow + column] = true;
            board[currentRow][column] = 'Q';

            backtrack(board, currentRow + 1, n, result, columns, obliqueLeft, obliqueRight);

            //回退放置
            columns[column] = false;
            obliqueLeft[currentRow + n - column - 1] = false;
            obliqueRight[currentRow + column] = false;
            board[currentRow][column] = '.';
        }
    }

}
