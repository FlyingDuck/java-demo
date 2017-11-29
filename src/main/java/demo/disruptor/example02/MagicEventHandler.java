package demo.disruptor.example02;


import com.lmax.disruptor.EventHandler;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/11/29 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class MagicEventHandler implements EventHandler<MagicEvent> {

    private String name;

    public MagicEventHandler(String name) {
        this.name = name;
    }


    @Override
    public void onEvent(MagicEvent event, long sequence, boolean endOfBatch) throws Exception {
        String magic = "MAGIC-"+this.name + "-" + sequence;
        event.setMagic(magic);
        System.out.println("event: " + event);
    }
}
