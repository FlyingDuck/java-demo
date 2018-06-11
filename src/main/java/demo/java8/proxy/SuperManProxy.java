package demo.java8.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Bennett Dong <br>
 * Date : 2018/6/11 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class SuperManProxy implements InvocationHandler {
    private Object targetObj;

    public SuperManProxy(Object targetObj) {
        this.targetObj = targetObj;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Method["+method.getName()+"] is invoked");

        return method.invoke(targetObj, args);
    }
}
