package SwordOffer;

import SwordOfferByNet.FindNumber3;
import cache.LocalCacheUtil;
import org.junit.Test;

/**
 * 第3题
 * 一个二维数组，每一行从左到右递增，每一列从上到下递增．
 * 输入一个二维数组和一个整数，判断数组中是否含有整数
 *
 * @author qgl
 * @date 2019/02/17
 */
public class Test3 {
    @Test
    public void test3() {
        int[][] testArray = {{1, 7, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        int target = 6;
        System.out.println("解法一：两个指针，数组中是否含有" + target + " ：" + FindNumber3.find(testArray, target));
        System.out.println("解法二：二分法，数组中是否含有" + target + " ：" + FindNumber3.find(target, testArray));
    }

    public static void main(String[] args) {
        LocalCacheUtil.getInstance().put("sheYang", "test1");
        for (int i = 0; i < 100; i++) {
            System.out.println(LocalCacheUtil.getInstance().getIfPresent("sheYang"));
        }
        System.out.println(LocalCacheUtil.getInstance().getKeysSize());
        System.out.println(LocalCacheUtil.getInstance().getCacheStats());
        LocalCacheUtil.getInstance().rebuild(10, 10);
        System.out.println(LocalCacheUtil.getInstance().getIfPresent("sheYang"));
        System.out.println(LocalCacheUtil.getInstance().getKeysSize());
        System.out.println(LocalCacheUtil.getInstance().getCacheStats());
        LocalCacheUtil.getInstance().put("sheYang", null);
        System.out.println(LocalCacheUtil.getInstance().getIfPresent("sheYang"));
    }
}
