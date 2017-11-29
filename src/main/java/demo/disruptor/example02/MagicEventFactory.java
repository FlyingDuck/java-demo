package demo.disruptor.example02;

import com.lmax.disruptor.EventFactory;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/11/29 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class MagicEventFactory implements EventFactory<MagicEvent> {

    @Override
    public MagicEvent newInstance() {
        return new MagicEvent("NO MAGIC NOW!!!");
    }
}
