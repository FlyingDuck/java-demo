package demo.dubbo.consumer;

import demo.dubbo.provider.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Bennett Dong <br>
 * Date : 2018/1/27 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class Consumer {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"dubbo/consumer.xml"});
        context.start();

        DemoService demoService = context.getBean("demoService", DemoService.class);

        demoService.sayHi();

    }
}
