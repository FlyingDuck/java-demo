package demo.hystrix;

import org.junit.Test;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/12/20 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class HelloWorldTest {

    @Test
    public void testHelloWorld() {
        CommandHelloWorld helloWorld = new CommandHelloWorld("Bennett");
        String result = helloWorld.execute();
        System.out.println(result);

        CommandHelloWorld virusHello = new CommandHelloWorld("Virus");
        result = virusHello.execute();
        System.out.println(result);
    }

    @Test
    public void testAsynchronousExecution() {
        Future<String> fBennett = new CommandHelloWorld("Bennett").queue();
        Future<String> fVirus = new CommandHelloWorld("Virus").queue();

        try {
            System.out.println(fBennett.get());
            System.out.println(fVirus.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testObservableHelloWorld() {
        ObservableCommandHelloWorld obHelloWorld = new ObservableCommandHelloWorld("Bennett");
        Observable<String> hotOb = obHelloWorld.observe();

        hotOb.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("Call"+ s);
            }
        });
    }

    @Test
    public void testObservableHelloWorld2() {
        Observable<String> worldOb = new ObservableCommandHelloWorld("World").observe();
        Observable<String> bennettOb = new ObservableCommandHelloWorld("Bennett").observe();

        // blocking
//        System.out.println(worldOb.toBlocking().single());
//        System.out.println(bennettOb.toBlocking().single());

        // non-blocking
        worldOb.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("Completed");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println("Error");
                e.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                System.out.println("Next: " + s);
            }
        });

        // non-blocking
        // - also verbose anonymous inner-class
        // - ignore errors and onCompleted signal
        bennettOb.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("Next:" + s);
            }
        });
    }

}
