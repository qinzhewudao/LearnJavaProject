package TestMath;

/**
 * created at 2019/6/30
 * <p>
 * 首先要注意的是它的返回值类型是long，如果 Math.round(11.5f)，那它的返回值类型就是int，这一点可以参考API
 * 其次 Returns the closest long to the argument, with ties rounding to positive infinity
 * 它返回的是一个最接近参数的long 值（例如：Math.round(11.6) = 12；Math.round(-11.6) = -12；Math.round(-0.1) = 0；Math.round(0.1) = 0），那如果出现向上向下距离一样的数值，比如题目中的11.5，该如何处理呢，别着急，看它的后半句话，with ties rounding to positive infinity（同时向正无穷方向取舍或者翻译成取较大的值，英语水平较差，只能翻译成这样了；
 * 例子:   Math.round(11.5) ，首先与 11.5最接近的有两个整数 11 和 12，取较大的那结果就是12；
 *            Math.round(-11.5)，首先与 -11.5最接近的有两个整数 -11 和 -12，取较大的那结果就是-11；
 *            Math.round(0.5)，首先与 0.5最接近的有两个整数 0 和 1，取较大的那结果就是1；
 *            Math.round(-0.5)，首先与 -0.5最接近的有两个整数 -1 和 0，取较大的那结果就是0；）
 * 然后它有三个特例：
 * 1.如果参数为 NaN（无穷与非数值），那么结果为 0。
 * 2.如果参数为负无穷大或任何小于等于 Long.MIN_VALUE 的值，那么结果等于Long.MIN_VALUE 的值。
 * 3.如果参数为正无穷大或任何大于等于 Long.MAX_VALUE 的值，那么结果等于Long.MAX_VALUE 的值。
 *
 * @author sheyang
 */
public class TestRound {

    public static void main(String[] args) {
        /*
          floor 向下取整
          ceil  向上取整
          round 则是4舍5入的计算，round方法，它表示“四舍五入”，算法为Math.floor(x+0.5)，
          即将原来的数字加上0.5后再向下取整，所以，Math.round(11.5)的结果为12，Math.round(-11.5)的结果为-11。

        Math.floor(1.4)=1.0
        Math.round(1.4)=1
        Math.ceil(1.4)=2.0
        Math.floor(1.5)=1.0
        Math.round(1.5)=2
        Math.ceil(1.5)=2.0
        Math.floor(1.6)=1.0
        Math.round(1.6)=2
        Math.ceil(1.6)=2.0
        Math.floor(-1.4)=-2.0
        Math.round(-1.4)=-1
        Math.ceil(-1.4)=-1.0
        Math.floor(-1.5)=-2.0
        Math.round(-1.5)=-1
        Math.ceil(-1.5)=-1.0
        Math.floor(-1.6)=-2.0
        Math.round(-1.6)=-2
        Math.ceil(-1.6)=-1.0

         */
        //四舍五入
        System.out.println(Math.round(-1.5));
        System.out.println(Math.round(0.5));


    }

}
