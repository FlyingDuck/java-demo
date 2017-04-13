package spring.test;

import demo.spring.helloworld.HelloApi;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Bennett Dong <br>
 * Date : 17/3/29 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class HelloTest {

    @Test
    public void testHelloWorld() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationcontext.xml");

        HelloApi helloApi = context.getBean("hello", HelloApi.class);

        helloApi.sayHello();
    }

}
