package TestMap;

import java.util.HashMap;
import java.util.Map;

/**
 * String 类型的底层结构是 char 数组，这个应该知道吧。
 * 所以，value.length 是字符串的长度。val[] 就是这个 char 数组。
 * 把 xy 带入到 for 循环中，这个 for 循环会循环 2 次。
 * 第一次循环：h=0，val[0]=x，所以 h=31*0+x，即 h=x。
 * 第二次循环：h=x，val[1]=y，所以 h=31*x+y。
 * 所以，经过计算后， xy 的 hashCode 为 31*x+y。
 * 同理可得，ab 的 hashCode 为 31*a+b。
 * 由于我们想要构建 hashCode 一样的字符串，所以可以得到等式：
 * 31x+y=31a+b
 * 那么问题就来了：请问 x，y，a，b 分别是多少？
 * 你算的出来吗？
 * 你算的出来个锤子！黑板上的排列组合你不是舍不得解开，你就是解不开。
 * 但是我可以解开，带大家看看这个题怎么搞。
 * 数学课开始了。注意，我要变形了。
 * 31x+y=31a+b 可以变形为：
 * 31x-31a=b-y。
 * 即，31(x-a)=b-y。
 * 这个时候就清晰很多了，很明显，上面的等式有一个特殊解：
 * x-a=1，b-y=31。
 * 因为，由上可得：对于任意两个字符串 xy 和 ab，如果它们满足 x-a=1，即第一个字符的 ASCII 码值相差为 1，同时满足 b-y=31，即第二个字符的 ASCII 码值相差为 -31。那么这两个字符的 hashCode 一定相等。
 * 都已经说的这么清楚了，这样的组合对照着 ASCII 码表来找，不是一抓一大把吗？
 * Aa 和 BB，对不对？
 * Ab 和 BC，是不是？
 * Ac 和 BD，有没有？
 *
 * @author sheyang
 * @date 2021/7/2 10:56 上午
 */
public class HashCodeSameMapTest {

    public static void main(String[] args) {
        System.out.println("Aa HashCode:" + "Aa".hashCode());
        System.out.println("BB HashCode:" + "BB".hashCode());
        System.out.println("Ab HashCode:" + "Ab".hashCode());
        System.out.println("BC HashCode:" + "BC".hashCode());
        System.out.println("Ac HashCode:" + "Ac".hashCode());
        System.out.println("BD HashCode:" + "BD".hashCode());

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("Aa", "Aa");
        hashMap.put("BB", "BB");
        hashMap.put("AaAa", "AaAa");
        hashMap.put("AaBB", "AaBB");
        hashMap.put("BBAa", "BBAa");
        hashMap.put("BBBB", "BBBB");
        hashMap.put("AaAaAa", "AaAaAa");
        hashMap.put("AaAaBB", "AaAaBB");
        hashMap.put("AaBBAa", "AaBBAa");
        hashMap.put("AaBBBB", "AaBBBB");
        hashMap.put("BBAaAa", "BBAaAa");
        hashMap.put("BBAaBB", "BBAaBB");
        hashMap.put("BBBBAa", "BBBBAa");
        hashMap.put("BBBBBB", "BBBBBB");
        System.out.println(hashMap);
    }
}
