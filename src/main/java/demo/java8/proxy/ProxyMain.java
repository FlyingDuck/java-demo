package demo.java8.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * Created by Bennett Dong <br>
 * Date : 2018/6/11 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class ProxyMain {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> proxyClz = Proxy.getProxyClass(ProxyMain.class.getClassLoader(), Man.class);
        Constructor<?> constructor = proxyClz.getConstructor(InvocationHandler.class);
        InvocationHandler invocationHandler = new SuperManProxy(new SuperMan());
        Man man = (Man) constructor.newInstance(invocationHandler);
        man.running();

        /*Man man = (Man) Proxy.newProxyInstance(
                ProxyMain.class.getClassLoader(),
                new Class<?>[]{Man.class},
                new SuperManProxy(new SuperMan()));
        man.running();*/
    }
}
