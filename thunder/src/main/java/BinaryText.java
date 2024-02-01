import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

/**
 * * author sheyang
 * * created at 2018/10/24
 * 文本
 * 样式包括粗体，斜体，下划线，中划线等多种情况，每种情况可以并存
 * 用 EnumSet 代替字符串处理
 */
public class BinaryText {

    private EnumSet<Style> set = EnumSet.noneOf(Style.class);

    private String[] texts = new String[Style.values().length];

    public BinaryText() {
        Arrays.fill(texts, "0");
    }

    public static void main(String[] args) {

        BinaryText text = new BinaryText();
        text.add(Style.BLOD, Style.ITALIC);
        text.add(Style.MIDDLELINE);

        System.out.println(text.contains(Style.BLOD));
        System.out.println(text.get());
    }

    public void add(Style... styles) {
        set.addAll(Arrays.asList(styles));
    }

    public void remove(Style... styles) {
        Arrays.asList(styles).forEach(set::remove);
    }

    public boolean contains(Style style) {
        return set.contains(style);
    }

    /**
     * 反序算位数，方便以后新增位
     *
     * @return
     */
    public String get() {
        set.forEach(e -> texts[e.val()] = "1");
        List<String> list = Arrays.asList(texts);
        Collections.reverse(list);
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : list) {
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    /**
     * 样式
     */
    public enum Style {
        BLOD(0),
        ITALIC(1),
        UNDERLINE(2),
        MIDDLELINE(3),
        ;

        private int val;

        Style(int i) {
            this.val = i;
        }

        public int val() {
            return val;
        }
    }
}
