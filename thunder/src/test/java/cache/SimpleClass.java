package cache;

/**
 * @author sheyang
 * @date 2021/6/15 4:39 下午
 */
public class SimpleClass {

    public int add(int x, int y) {
        return x + y;
    }

    public void plus(int x, int y) {
        System.out.println("HaHaHa");
    }

    public static void main(String[] args) {
        SimpleClass simpleClass = (SimpleClass) new CglibProxy("SimpleClass").newInstall(new SimpleClass());
        simpleClass.add(1, 2);
        simpleClass.plus(3, 4);
    }

}
