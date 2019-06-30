package util.sheyang;

/**
 * author sheyang
 * created at 2018/10/24
 */

import com.sun.deploy.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

/**
 * 文本
 * 样式包括粗体，斜体，下划线，中划线等多种情况，每种情况可以并存
 * 用 EnumSet 代替字符串处理
 */
public class BinaryText {

    private EnumSet<Style> set = EnumSet.noneOf(Style.class);

    private String[] texts = new String[Style.values().length];

    public BinaryText() {
        for (int i = 0; i < texts.length; i++) {
            texts[i] = "0";
        }
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
        set.forEach(e -> {
            texts[e.val()] = "1";
        });
        List<String> list = Arrays.asList(texts);
        Collections.reverse(list);
        return StringUtils.join(list, "");
    }

    /**
     * 样式
     */
    public enum Style {
        BLOD(0),
        ITALIC(1),
        UNDERLINE(2),
        MIDDLELINE(3),;

        private int val;

        Style(int i) {
            this.val = i;
        }

        public int val() {
            return val;
        }
    }
}