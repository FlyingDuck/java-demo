package demo.spring.test.aop;

import demo.spring.helloworld.HelloApi;
import demo.spring.helloworld.HelloApiImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Bennett Dong <br>
 * Date : 17/4/13 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class AopTest {

    @Test
    public void testHelloApiAop() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationcontext.xml");

        HelloApi helloApi = context.getBean("helloApi", HelloApi.class);

        helloApi.sayHello();

    }

}
