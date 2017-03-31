package demo.java8.lambda;

/**
 * Created by dongsj on 2017/3/31.
 */
public interface Function<T, R> {
    R apply(T t);
}
