package Algorithmn.leetcode;

/**
 * created at 2019/7/20
 *
 * @author sheyang
 */
public class leetcode12 {

    public String intToRoman(int num) {
        // 把阿拉伯数字与罗马数字可能出现的所有情况和对应关系，放在两个数组中
        // 并且按照阿拉伯数字的大小降序排列，这是贪心选择思想
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (num > 0) {
            if(num >= nums[index]){
                sb.append(romans[index]);
                num -= nums[index];
            } else {
                index++;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args){
        leetcode12 leetcode12 = new leetcode12();
        System.out.println(leetcode12.intToRoman(3781));
    }

}
