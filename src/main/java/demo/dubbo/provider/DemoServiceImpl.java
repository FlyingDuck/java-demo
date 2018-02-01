package demo.dubbo.provider;

/**
 * Created by Bennett Dong <br>
 * Date : 2018/1/27 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class DemoServiceImpl implements DemoService {

    @Override
    public void sayHi() {
        System.out.println("Hello Dubbo");
    }
}
