package demo.java8.test;

import demo.java8.lambda.Function;
import org.junit.Test;

/**
 * Created by dongsj on 2017/3/31.
 */
public class LambdaTest {


    @Test
    public void test1() {
        // 一般实现
        Function<Long, Boolean> functionNormal = new Function<Long, Boolean>() {
            @Override
            public Boolean apply(Long aLong) {
                return aLong > 0;
            }
        };
        System.out.println(functionNormal.apply(10L));

        // Lambda表达式
        Function<Long, Boolean> functionLambda = x -> x > 0;
        System.out.println(functionLambda.apply(10L));
    }

}
