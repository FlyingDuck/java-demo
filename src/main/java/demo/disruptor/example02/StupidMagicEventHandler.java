package demo.disruptor.example02;


import com.lmax.disruptor.EventHandler;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/11/29 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class StupidMagicEventHandler implements EventHandler<MagicEvent> {

    @Override
    public void onEvent(MagicEvent event, long sequence, boolean endOfBatch) throws Exception {
//        Thread.sleep(10);

        String magic = "MAGIC-Stupid" + sequence;
        event.setMagic(magic);
        System.out.println("event: " + event);
    }
}
