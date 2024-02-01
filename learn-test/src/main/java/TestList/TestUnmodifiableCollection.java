package TestList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * created at 2019/6/30
 *
 * @author sheyang
 */
public class TestUnmodifiableCollection {

    /**
     * 怎么确保一个集合不能被修改？
     * <p>
     * 可以使用 Collections. unmodifiableCollection(Collection c) 方法来创建一个只读集合，这样改变集合的任何操作都会抛出 Java. lang. UnsupportedOperationException 异常。
     * ---------------------
     * 作者：MrBlackWhite
     * 来源：CSDN
     * 原文：https://blog.csdn.net/u011665991/article/details/89206148
     * 版权声明：本文为博主原创文章，转载请附上博文链接！
     *
     * @param args
     */
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("x");
        Collection<String> clist = Collections.unmodifiableCollection(list);
        // 运行时此行报错
        clist.add("y");
        System.out.println(list.size());

    }

}
