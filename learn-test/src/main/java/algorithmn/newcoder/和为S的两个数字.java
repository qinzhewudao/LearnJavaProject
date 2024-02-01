package algorithmn.newcoder;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author sheyang
 * @description
 * @date 2019/8/11
 * @time 上午11:09
 */
public class 和为S的两个数字 {

    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        int min =  0;
        int left = 0;
        int max = 0 ;
        int right = array.length - 1;
        boolean first = true;
        int product = 0;
        while(left < right){
            if(sum == array[left] + array[right]){
                int tmpProduct = array[left] * array[right];
                if(first || product > tmpProduct){
                    min = array[left];
                    max = array[right];
                    product = tmpProduct;
                    first = false;
                }
                while(left + 1 < right && array[left] == array[left+1]){
                    left++;
                }
                while(right + 1 > left && array[right] == array[right-1]){
                    right--;
                }
                left++;
                right--;
            } else if(sum > array[left] + array[right]){
                left++;
            } else if(sum < array[left] + array[right]) {
                right--;
            }
        }

        if(first){
            return new ArrayList<>();
        }

        return new ArrayList<>(Arrays.asList(min,max));
    }

    public static void main(String[] args){
        和为S的两个数字 solution = new 和为S的两个数字();
        System.out.println(solution.FindNumbersWithSum(new int[]{1, 2, 4, 7, 11, 15}, 15));
    }


}
