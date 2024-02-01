package Algorithmn.leetcode;

/**
 * created at 2019/7/21
 *
 * @author sheyang
 */
public class leetcode36 {

    public boolean isValidSudoku(char[][] board) {
        int[][] rowNumCounts = new int[9][9];
        int[][] colNumCounts = new int[9][9];
        int[][] matrixNumCounts = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0' - 1;
                    rowNumCounts[i][num]++;
                    colNumCounts[j][num]++;
                    int matrixIndex = 3 * (i / 3) + j / 3;
                    matrixNumCounts[matrixIndex][num]++;
                    if (rowNumCounts[i][num] > 1
                            || colNumCounts[j][num] > 1
                            || matrixNumCounts[matrixIndex][num] > 1) {
                        return false;
                    }

                }
            }
        }
        return true;
    }

}
