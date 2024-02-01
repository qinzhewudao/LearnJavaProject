package algorithmn.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * created at 2019/7/27
 *
 * @author sheyang
 */
public class leetcode54 {

    public List<Integer> spiralOrder(int[][] matrix) {

        int m = matrix.length;
        if (0 == m) {
            return new ArrayList<>(0);
        }
        int n = matrix[0].length;
        if (0 == n) {
            return new ArrayList<>(0);
        }

        List<Integer> result = new LinkedList<>();

        int rowUp = 0;
        int rowDown = m - 1;
        int colLeft = 0;
        int colRight = n - 1;

        while (rowUp <= rowDown && colLeft <= colRight) {

            for (int col = colLeft; col <= colRight; col++) {
                result.add(matrix[rowUp][col]);
            }

            for (int row = rowUp + 1; row <= rowDown; row++) {
                result.add(matrix[row][colRight]);
            }

            if (colLeft < colRight && rowUp < rowDown) {
                for (int col = colRight - 1; col > colLeft; col--) {
                    result.add(matrix[rowDown][col]);
                }
                for (int row = rowDown; row > rowUp; row--) {
                    result.add(matrix[row][colLeft]);
                }
            }

            rowUp++;
            rowDown--;
            colLeft++;
            colRight--;
        }
        return result;
    }

    public static void main(String[] args) {
        leetcode54 leetcode54 = new leetcode54();
        System.out.println(leetcode54.spiralOrder(new int[][]{{6, 9, 7}}));
    }

}
