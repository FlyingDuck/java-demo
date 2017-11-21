package demo.disruptor.example01;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/11/21 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class LongEventMain {

    public static void main(String[] args) throws Exception {
//        Executor executor = Executors.newCachedThreadPool();

        LongEventFactory eventFactory = new LongEventFactory();

        int bufferSize = 1024;

        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(eventFactory, bufferSize, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                System.err.println("New Thread");
                Thread thread = new Thread(r);
                thread.setName("DSJ:"+System.currentTimeMillis());
                return thread;
            }
        });

        disruptor.handleEventsWith(new LongEventHandler());

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducer producer1 = new LongEventProducer(ringBuffer);
        LongEventProducer producer2 = new LongEventProducer(ringBuffer);

        ByteBuffer byteBuffer = ByteBuffer.allocate(8);

        for (long l = 0; true; l++)
        {
            byteBuffer.putLong(0, l);
            producer1.onData(byteBuffer);
            byteBuffer.putLong(0, ++l);
            producer2.onData(byteBuffer);
            Thread.sleep(1000);
        }

    }

}
