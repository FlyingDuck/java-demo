package demo.disruptor.example01;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/11/21 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class LongEventProducer {

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer byteBuffer) {
        long sequence = this.ringBuffer.next();
        try {
            LongEvent event = this.ringBuffer.get(sequence);
            event.setValue(byteBuffer.getLong(0));
        } finally {
            ringBuffer.publish(sequence);
        }

    }
}
