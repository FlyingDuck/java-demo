package demo.disruptor.example02;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/11/29 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class MagicEvent {

    private String magic;

    public MagicEvent() {}

    public MagicEvent(String magic) {
        this.magic = magic;
    }

    public String getMagic() {
        return magic;
    }

    public void setMagic(String magic) {
        this.magic = magic;
    }

    @Override
    public String toString() {
        return "MagicEvent{" +
                "magic='" + magic + '\'' +
                '}';
    }
}
