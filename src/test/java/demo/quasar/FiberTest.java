package demo.quasar;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.FiberForkJoinScheduler;
import co.paralleluniverse.fibers.FiberScheduler;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.SuspendableRunnable;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/12/6 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class FiberTest {

    private FiberScheduler fiberScheduler = new FiberForkJoinScheduler("ForkJoin", 4, null, false);

    @Test
    public void testTimeout() throws Exception {
        Fiber fiber = new Fiber(fiberScheduler, new SuspendableRunnable() {
            @Override
            public void run() throws SuspendExecution {
                Fiber.park(100, TimeUnit.MILLISECONDS);
            }
        }).start();

        try {
            fiber.join(50, TimeUnit.MILLISECONDS);
            Assert.fail();
        } catch (TimeoutException e) {
        }

        fiber.join(200, TimeUnit.MILLISECONDS);
    }

    @Test
    public void testInterrupt() {

    }
}
