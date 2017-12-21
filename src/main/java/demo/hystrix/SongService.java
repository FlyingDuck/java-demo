package demo.hystrix;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/12/22 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class SongService {

    public String sing(String songName) {
        return "Sing, sing, sing the " + songName;
    }
}
