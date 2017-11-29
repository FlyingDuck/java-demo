package demo.disruptor.example02;

import com.lmax.disruptor.*;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/11/29 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class OneToThreeDiamondSequencedMain {



    public static void main(String[] args) throws InterruptedException {
        final ExecutorService executor = Executors.newFixedThreadPool(10, DaemonThreadFactory.INSTANCE);

        MagicEventFactory eventFactory = new MagicEventFactory();
        YieldingWaitStrategy waitStrategy = new YieldingWaitStrategy();

        RingBuffer<MagicEvent> ringBuffer = RingBuffer.createSingleProducer(eventFactory, 1024, waitStrategy);

        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();

        FastMagicEventHandler fastMagicEventHandler = new FastMagicEventHandler("A");
        BatchEventProcessor<MagicEvent> fastProcessor = new BatchEventProcessor<>(ringBuffer, sequenceBarrier, fastMagicEventHandler);

        SlowMagicEventHandler slowMagicEventHandler = new SlowMagicEventHandler("B");
        BatchEventProcessor<MagicEvent> slowProcessor = new BatchEventProcessor<>(ringBuffer, sequenceBarrier, slowMagicEventHandler);

//        SequenceGroup sequenceGroup = new SequenceGroup();
//        sequenceGroup.add(fastProcessor.getSequence());
//        sequenceGroup.add(slowProcessor.getSequence());
//        SequenceBarrier stupidSequenceBarrier = ringBuffer.newBarrier(sequenceGroup);
        SequenceBarrier stupidSequenceBarrier = ringBuffer.newBarrier(fastProcessor.getSequence(), slowProcessor.getSequence());
        StupidMagicEventHandler stupidMagicEventHandler = new StupidMagicEventHandler();
        BatchEventProcessor<MagicEvent> abProcessor = new BatchEventProcessor<>(ringBuffer, stupidSequenceBarrier, stupidMagicEventHandler);

        ringBuffer.addGatingSequences(abProcessor.getSequence());
//        WorkProcessor<MagicEvent> processor01 = new WorkProcessor<>(ringBuffer, sequenceBarrier, )

        executor.submit(fastProcessor);
        executor.submit(slowProcessor);
        executor.submit(abProcessor);

        for (long i = 0; i < 100; i++) {
            long sequence = ringBuffer.next();
            ringBuffer.get(sequence).setMagic("["+i+"]");
            ringBuffer.publish(sequence);
            Thread.sleep(6);
        }

        Thread.sleep(20*1000);

        fastProcessor.halt();
        slowProcessor.halt();
        abProcessor.halt();
    }

}
