package demo.dubbo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Bennett Dong <br>
 * Date : 2018/1/27 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class Provider {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"dubbo/provider.xml"});
        context.start();
        System.in.read();
    }


}
