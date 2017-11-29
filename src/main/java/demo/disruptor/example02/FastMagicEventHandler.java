package demo.disruptor.example02;


import com.lmax.disruptor.EventHandler;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/11/29 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class FastMagicEventHandler implements EventHandler<MagicEvent> {

    private String name;

    public FastMagicEventHandler(String name) {
        this.name = name;
    }


    @Override
    public void onEvent(MagicEvent event, long sequence, boolean endOfBatch) throws Exception {
        Thread.sleep(20);
        /*if (sequence == 30) {
            Thread.sleep(10000);
        }*/

        String magic = "MAGIC-"+this.name + "-" + sequence;
        event.setMagic(magic);
        System.out.println("event: " + event);
    }
}
