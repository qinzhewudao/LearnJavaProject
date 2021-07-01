package Disruptor;

/**
 * created at 2019/6/22
 *
 * @author sheyang
 */
public class LongEvent {

    private long value;

    public void set(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
