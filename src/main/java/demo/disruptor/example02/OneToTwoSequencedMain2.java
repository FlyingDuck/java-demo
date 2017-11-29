package demo.disruptor.example02;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/11/29 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class OneToTwoSequencedMain2 {


    public static void main(String[] args) throws InterruptedException {
        MagicEventFactory eventFactory = new MagicEventFactory();
        YieldingWaitStrategy waitStrategy = new YieldingWaitStrategy();
        final ExecutorService executor = Executors.newFixedThreadPool(10, DaemonThreadFactory.INSTANCE);

        Disruptor<MagicEvent> disruptor =
                new Disruptor<>(eventFactory, 1024, DaemonThreadFactory.INSTANCE, ProducerType.SINGLE, waitStrategy);

        FastMagicEventHandler fastMagicEventHandler = new FastMagicEventHandler("A");
        SlowMagicEventHandler slowMagicEventHandler = new SlowMagicEventHandler("B");
//        disruptor.handleEventsWithWorkerPool();
        disruptor.handleEventsWith(fastMagicEventHandler).handleEventsWith(slowMagicEventHandler);
        disruptor.start();

        RingBuffer<MagicEvent> ringBuffer = disruptor.getRingBuffer();


        for (long i = 0; i < 100; i++) {
            long sequence = ringBuffer.next();
            ringBuffer.get(sequence).setMagic("["+i+"]");
            ringBuffer.publish(sequence);
            Thread.sleep(100);
        }

        Thread.sleep(20*1000);


        /*MagicEventFactory eventFactory = new MagicEventFactory();
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
        slowProcessor.halt();*/
    }

}
