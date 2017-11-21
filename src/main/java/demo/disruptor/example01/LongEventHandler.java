package demo.disruptor.example01;


import com.lmax.disruptor.EventHandler;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/11/21 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println("Event: " + longEvent);
    }
}
