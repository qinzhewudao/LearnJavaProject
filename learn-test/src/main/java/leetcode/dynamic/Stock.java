package leetcode.dynamic;

/**
 * @author sheyang
 * @date 2021/7/5 5:15 下午
 */
public class Stock {

    public static void main(String[] args) {
        int[] a = new int[]{7, 1, 5, 3, 6, 4};
        int minPrice = Integer.MAX_VALUE;
        int result = 0;
        for (int j : a) {
            minPrice = Math.min(minPrice, j);
            if (j < minPrice) {
                minPrice = j;
            } else {
                result = Math.max(result, j - minPrice);
            }
        }
        System.out.println(result);
    }

}

