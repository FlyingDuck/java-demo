package demo.disruptor.example01;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/11/21 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class LongEvent {
    private long value;

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LongEvent{" +
                " thread=" + Thread.currentThread().getName() +
                " value=" + value +
                '}';
    }
}
