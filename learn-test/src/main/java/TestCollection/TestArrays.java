package TestCollection;

/**
 * author sheyang
 * created at 2018/8/22
 */
//导入 Arrays 类, 这样才能用它的方法

import java.util.Arrays;

public class TestArrays {
    public static void main(String[] args) {
        //定义一个 a 数组, 使用静态初始化
        int[] a = new int[]{3, 4, 5, 6};
        //定义一个 b 数组, 使用静态初始化
        int[] b = new int[]{3, 4, 5, 6};

        //使用 Arrays类的 equals 方法.
        //boolean equals(type[] a, type[] b) 如果 a 数组 和 b 数组长度相等,元素依次相同,则会返回 true
        //如果 a 数组 和 b 数组 长度相等, 每个元素依次相等, 将会输出 true
        System.out.println("a 数组 和 b数组 是否相等呢?" + Arrays.equals(a, b));

        //使用 Arrays类的 copyOf 方法.
        //type[] copyOf(type[] original, int length)
        //这个方法会把 original(原件) 数组复制成一个新数组, 其中 length 是新数组的长度.
        //如果 length 小于 original 数组的长度, 新数组就是原数组前面 length 个元素.
        //如果 length 大于 original 数组的长度, 新数组前面元素就是原数组的所有元素,
        //后面补充 0(数值类型) / false(布尔类型) / null(引用类型) 看原数组是啥类型.
        //复制 a 数组, 生成一个 a2 数组, 然后对比是否相等
        int[] a2 = Arrays.copyOf(a, 6);
        System.out.println("a 数组 和 a2 数组 是否相等呢?" + Arrays.equals(a, a2));

        //使用 Arrays类的 toString 方法
        //String toString(type[] a)
        //该方法将一个数组转换成一个字符串进行输出. 该方法按照顺序把多个数组元素连起来,
        //多个数组元素之间使用 英文逗号 , 和空格隔开.
        //输出 a2 数组
        System.out.println("a2 数组的元素为:" + Arrays.toString(a2));

        //使用 Arrays 类的 fill 方法
        //void fill(type[]a, type val) 该方法将会把 a 数组的所有元素都赋值为 val
        //void fill(type[]a, int formIndex, int toIndex, type val)
        //该方法和上面方法作用相同, 区别是将数组a 的 fromIndex 到 toIndex 索引处的数组元素赋值为 val
        //将 a2 数组的第 3个元素(包括) 到第 5个元素(不包括) 赋值为 1
        Arrays.fill(a2, 2, 4, 1);
        //输出 a2 数组元素看看
        System.out.println("a2 数组的元素为:" + Arrays.toString(a2));

        //使用 Arrays 类的 sort 方法
        //void sort(type[] a) 该方法对 数组a 的元素进行排序.
        //void sort(type[] a, int formIndex, int toIndex) 该方法和上面方法类似, 区别是
        //仅仅对 fromIndex 到 toIndex 索引的元素进行排序.
        //对 a2 数组进行排序
        Arrays.sort(a2);
        //输出 a2 数组
        System.out.println("a2 数组的元素为:" + Arrays.toString(a2));
    }
}
