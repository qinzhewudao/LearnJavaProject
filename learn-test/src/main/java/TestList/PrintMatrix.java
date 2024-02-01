package TestList;

/**
 * created at 2019/6/20
 * 打印蛇形矩阵
 *
 * @author sheyang
 */
public class PrintMatrix {

    private static int num = 1;

    public static void main(String[] args) {

        int[][] m = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};

        int tR = 0, tC = 0, dR = 3, dC = 3;
        while (tR <= dR && tC <= dC) {
            printEdge(m, tR++, tC++, dR--, dC--);
        }

//        //给出多大的数字，就构造多大的矩阵
//        int length = 4;
//        int[][] m = new int[length][length];
//
//        int tR = 0, tC = 0, dR = m.length - 1, dC = m[0].length - 1;
//        while (tR <= dR && tC <= dC) {
//            generateEdge(m, tR++, tC++, dR--, dC--);
//        }
//
//        for (int[] aM : m) {
//            for (int anAM : aM) {
//                System.out.print(anAM + "\t");
//            }
//            System.out.println();
//        }
    }

    /**
     * 打印蛇形数组
     *
     * @param m
     * @param tR
     * @param tC
     * @param dR
     * @param dC
     */
    private static void generateEdge(int[][] m, int tR, int tC, int dR, int dC) {
        //处理length为奇数，最中间一个元素
        if (tR == dR) {
            m[tR][tC] = num;
        } else {
            int curRow = tR;
            int curCol = tC;
            while (curCol != dC) {
                m[tR][curCol] = num;
                num++;
                curCol++;
            }
            while (curRow != dR) {
                m[curRow][dC] = num;
                num++;
                curRow++;
            }
            while (curCol != tC) {
                m[dR][curCol] = num;
                num++;
                curCol--;
            }
            while (curRow != tR) {
                m[curRow][tC] = num;
                num++;
                curRow--;
            }
        }
    }

    /**
     * 循环打印数组
     *
     * @param m
     * @param tR
     * @param tC
     * @param dR
     * @param dC
     */
    private static void printEdge(int[][] m, int tR, int tC, int dR, int dC) {
        if (tR == dR) {
            for (int i = tC; i <= dC; i++) {
                System.out.print(m[tR][i] + " ");
            }
        } else if (tC == dC) {
            for (int i = tR; i <= dR; i++) {
                System.out.print(m[i][tC] + " ");
            }
        } else {
            int curRow = tR;
            int curCol = tC;
            while (curCol != dC) {
                System.out.print(m[tR][curCol] + " ");
                curCol++;
            }
            while (curRow != dR) {
                System.out.print(m[curRow][dC] + " ");
                curRow++;
            }

            while (curCol != tC) {
                System.out.print(m[dR][curCol] + " ");
                curCol--;
            }

            while (curRow != tR) {
                System.out.print(m[curRow][tC] + " ");
                curRow--;
            }

        }
    }

}