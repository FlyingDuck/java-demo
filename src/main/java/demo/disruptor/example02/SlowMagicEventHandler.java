package demo.disruptor.example02;


import com.lmax.disruptor.EventHandler;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/11/29 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class SlowMagicEventHandler implements EventHandler<MagicEvent> {

    private String name;

    public SlowMagicEventHandler(String name) {
        this.name = name;
    }


    @Override
    public void onEvent(MagicEvent event, long sequence, boolean endOfBatch) throws Exception {
        Thread.sleep(50);
        /*if (sequence == 20) {
            Thread.sleep(10000);
        }*/


        String magic = "MAGIC-"+this.name + "-" + sequence;
        event.setMagic(magic);
        System.out.println("event: " + event);
    }
}
