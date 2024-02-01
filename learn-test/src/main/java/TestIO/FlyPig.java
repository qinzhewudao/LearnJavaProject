package TestIO;

import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class FlyPig implements Serializable {
    private static final long serialVersionUID = 1L;
    private static String AGE = "269";
    //初始化version版本号
    private final long version = 2L;
    /*
    串行化版本统一标识符-serialVersionUID
    java通过一个名为UID（stream unique identifier）来控制，这个UID是隐式的，它通过类名，
    方法名等诸多因素经过计算而得，理论上是一一映射的关系，也就是唯一的。如果UID不一 样的话，
    就无法实现反序列化了，并且将会得到InvalidClassException。在继承上面两个接口时，我们并
    没有看到要实现个UID。默认地，系统帮我们实现了这一个操作。实际上，更推荐自己显示的写一个
    UID会更好。
    向上兼容：指老的版本能够读取新的版本序列化的数据流。因为在java中serialVersionUID是唯
    一控制着能否反序列化成功的标志，只要这个值不一样，就无法反序列化成功。但只要这个值相同，
    无论如何都将反序列化，在这个过程中，对于向上兼容性，新数据流中的多余的内容将会被忽略；对
    于向下兼容性而言，旧的数据流中所包含的所有内容都 将会被恢复，新版本的类中没有涉及到的部
    分将保持默认值。利用这一特性，可以说，只要我们认为的保持serialVersionUID不变，向上兼容
    性是 自动实现的。
    向下兼容：老的版本能够读取新的数据序列流。有些新的字段可能会没有值，所以需要由一个版本号
    来进行区分维护，以适应读取不同的数据流的要求。
     */
    private String name;
    private String color;
    transient private String car;
    private String addTip;

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        if (version == 1L) { //1版本
            out.writeObject(addTip);
        } else if (version == 2L) { //2版本
            out.writeObject(addTip);
        } else {
            throw new InvalidClassException("请升级版本"); //抛出异常了
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getAddTip() {
        return addTip;
    }

    public void setAddTip(String addTip) {
        this.addTip = addTip;
    }

    @Override
    public String toString() {
        return "FlyPig{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", car='" + car + '\'' +
                ", AGE='" + AGE + '\'' +
                ", addTip='" + addTip + '\'' +
                '}';
    }
}
