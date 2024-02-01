package algorithmn.leetcode;

/**
 * @author sheyang
 * @description
 * @date 2019/9/1
 * @time 下午6:31
 */
public class leetcode74 {

    public boolean searchMatrix(int[][] matrix, int target) {
        int col = matrix.length;
        if (col <= 0) {
            return false;
        }
        int row = matrix[0].length;
        if (row <= 0) {
            return false;
        }
        int i;
        for (i = 0; i < col; i++) {
            if (matrix[i][row - 1] > target) {
                break;
            } else if (matrix[i][row - 1] < target) {
                i++;
            } else {
                return true;
            }
        }
        if (i >= col) {
            return false;
        }
        for (int j = row - 1; j >= 0; ) {
            if (matrix[i][j] > target) {
                j--;
            } else {
                return matrix[i][j] >= target;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        leetcode74 leetcode74 = new leetcode74();
        System.out.println(leetcode74.searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}}, 3));
    }

}
