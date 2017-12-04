package demo.quasar;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.Strand;
import co.paralleluniverse.strands.channels.Channel;
import co.paralleluniverse.strands.channels.Channels;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/12/2 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class Example01 {

    public static void main(String[] args) throws Exception {
        Channel<Integer> naturalCh = Channels.newChannel(-1);
        Channel<Integer> squareCh = Channels.newChannel(-1);



        Fiber<Integer> naturalFiber = new Fiber<Integer>(() -> {
            String fiberName = Fiber.currentFiber().getName() + "-ID=" + Fiber.currentFiber().getId();
            for (int i=0; i<10; i++) {
                naturalCh.send(i);
                System.out.println("["+fiberName+"]-Send-"+i);
                Strand.sleep(10);
            }
            naturalCh.close();
        }).start();

        Fiber<Integer> squareFiber = new Fiber<Integer>(() -> {
            String fiberName = Fiber.currentFiber().getName() + "-ID=" + Fiber.currentFiber().getId();
            Integer value = 0;
            while ((value=naturalCh.receive()) != null) {
                System.out.println("["+fiberName+"]-Receive-"+value);
                squareCh.send(value * value);
            }
            squareCh.close();
        }).start();



        printer(squareCh);

//        squareFiber.join();
    }

    private static void printer(Channel<Integer> inCh) throws InterruptedException, SuspendExecution {

        String threadName = Thread.currentThread().getName() + "-ID=" + Thread.currentThread().getId();
        Integer value = null;
        while ((value = inCh.receive()) != null) {
            System.out.println("["+threadName+"]-Value:" + value);
        }
    }
}
