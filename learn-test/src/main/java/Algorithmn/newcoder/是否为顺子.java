package Algorithmn.newcoder;

/**
 * @author sheyang
 * @description
 * @date 2019/8/11
 * @time 上午11:55
 */
public class 是否为顺子 {

    public boolean isContinuous(int[] numbers) {
        if(null == numbers || numbers.length < 5){
            return false;
        }
        int[] d = new int[14];
        d[0] = -5;
        int max = -1;
        int min = 14;
        for (int number : numbers) {
            d[number]++;
            if (number == 0) {
                continue;
            }
            if (d[number] > 1) {
                return false;
            }
            max = number > max ? number : max;
            min = number < min ? number : min;
        }
        return max - min < 5;
    }

}
