package demo.disruptor.example02;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/11/29 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class OneToTwoSequencedMain {


    public static void main(String[] args) {
        final ExecutorService executor = Executors.newFixedThreadPool(10, DaemonThreadFactory.INSTANCE);

        MagicEventFactory eventFactory = new MagicEventFactory();
        YieldingWaitStrategy waitStrategy = new YieldingWaitStrategy();

        RingBuffer<MagicEvent> ringBuffer = RingBuffer.createSingleProducer(eventFactory, 1024, waitStrategy);

        SequenceBarrier stepOneSequenceBarrier = ringBuffer.newBarrier();

        FastMagicEventHandler fastMagicEventHandler = new FastMagicEventHandler("A");
        BatchEventProcessor<MagicEvent> fastProcessor =
                new BatchEventProcessor<>(ringBuffer, stepOneSequenceBarrier, fastMagicEventHandler);

        SequenceBarrier stepTwoSequenceBarrier = ringBuffer.newBarrier(fastProcessor.getSequence());
        SlowMagicEventHandler slowMagicEventHandler = new SlowMagicEventHandler("B");
        BatchEventProcessor<MagicEvent> slowProcessor =
                new BatchEventProcessor<>(ringBuffer, stepTwoSequenceBarrier, slowMagicEventHandler);


        ringBuffer.addGatingSequences(slowProcessor.getSequence());

        executor.submit(fastProcessor);
        executor.submit(slowProcessor);

        for (long i = 0; i < 100; i++) {
            long sequence = ringBuffer.next();
            ringBuffer.get(sequence).setMagic("["+i+"]");
            ringBuffer.publish(sequence);
        }

        try {
            Thread.sleep(20*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fastProcessor.halt();
        slowProcessor.halt();
    }

}
