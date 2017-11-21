package demo.disruptor.example01;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import io.netty.buffer.ByteBuf;

import java.nio.ByteBuffer;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/11/21 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class LongEventProducerWithTranslator {

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR = new EventTranslatorOneArg<LongEvent, ByteBuffer>() {
        @Override
        public void translateTo(LongEvent longEvent, long sequence, ByteBuffer byteBuffer) {
            longEvent.setValue(byteBuffer.getLong(0));
        }
    };

    public void onData(ByteBuffer byteBuffer) {
        ringBuffer.publishEvent(TRANSLATOR, byteBuffer);
    }

}
