package demo;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.FiberForkJoinScheduler;
import co.paralleluniverse.fibers.FiberScheduler;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.SuspendableRunnable;

import java.util.concurrent.*;

/**
 * Created by dongsj on 17/3/4.
 *
 */
public class Main {

    public static void main(String[] args) {
        FiberScheduler fiberScheduler = new FiberForkJoinScheduler("ForkJoin", 4, null, false);

        try {
            Fiber fiber = new Fiber(fiberScheduler, new SuspendableRunnable() {
                @Override
                public void run() throws SuspendExecution {
                    Fiber.park(100, TimeUnit.MILLISECONDS);
                }
            }).start();

            try {
                fiber.join(50, TimeUnit.MILLISECONDS);
            } catch (TimeoutException e) {
                System.out.println("Timeout");
            }

            fiber.join(200, TimeUnit.MILLISECONDS);
            System.out.println("End");
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

    }
}
