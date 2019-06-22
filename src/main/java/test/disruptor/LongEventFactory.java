package test.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * created at 2019/6/22
 *
 * @author sheyang
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }

}
