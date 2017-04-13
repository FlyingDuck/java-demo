package demo.spring.aop;

/**
 * Created by Bennett Dong <br>
 * Date : 17/4/13 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class HelloWorldAspect {


    public void beforeAdvice() {
        System.out.println("[Aspect] Before Advice");
    }

    public void afterFinallyAdvice() {
        System.out.println("[Aspect] After Finally Advice");
    }

}
