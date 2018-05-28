package demo.quasar;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.fibers.Suspendable;
import co.paralleluniverse.strands.Strand;
import co.paralleluniverse.strands.SuspendableCallable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by Bennett Dong <br>
 * Date : 2018/5/16 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class Example02 {

    public static void main(String[] args) throws InterruptedException {
        final int count = 100000;
        threadWorker(count);  // Thread: [1000000]count [58205]duration [11536670]total-time [11]latency
        fiberWorker(count); // Fiber: [1000000]count [7490]duration [2913864899]total-time [2913]latency

//        Thread Fork/Join
//        JDK proxy

    }

    @Suspendable
    static void funcA() throws InterruptedException, SuspendExecution {
        funcB();
    }

    @Suspendable
    static void funcB() throws InterruptedException, SuspendExecution {
        /*int sum = 0;
        for (int i=0; i<10000; i++) {
            sum += i;
        }*/
        //TimeUnit.MILLISECONDS.sleep(1000);
        Strand.sleep(10);
    }


    static void threadWorker(final int count) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(count);
        LongAdder total = new LongAdder();
        ExecutorService es = Executors.newFixedThreadPool(200);

        long startAll = System.currentTimeMillis();
        for (int i=0; i<count; i++) {
            es.submit(() -> {
                long start = System.currentTimeMillis();

                try {
                    funcA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (SuspendExecution suspendExecution) {
                    suspendExecution.printStackTrace();
                }

                total.add(System.currentTimeMillis() -start);
                latch.countDown();
            });
        }

        latch.await();
        System.out.println("Thread: ["+count+"]count ["+(System.currentTimeMillis()-startAll)+"]duration ["+total.longValue()+"]total-time ["+total.longValue()/count+"]latency");

        es.shutdown();
    }


    static void fiberWorker(final int count) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(count);
        LongAdder total = new LongAdder();

        long startAll = System.currentTimeMillis();
        for (int i=0; i<count; i++) {

            Fiber<Integer> tmpFiber = new Fiber<>((SuspendableCallable<Integer>) () -> {
                long start = System.currentTimeMillis();

                funcA();

                total.add(System.currentTimeMillis() -start);
                latch.countDown();

                return 1;
            });
            tmpFiber.start();
        }

        latch.await();
        System.out.println("Fiber: ["+count+"]count ["+(System.currentTimeMillis()-startAll)+"]duration ["+total.longValue()+"]total-time ["+total.longValue()/count+"]latency");
    }

}
