package demo.java8.exception;

/**
 * Created by Bennett Dong <br>
 * Date : 2018/5/22 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class ExceptionMain {

    public static void main(String[] args) {

        try {
            System.out.println("Hello");
        } finally {
            finalMethod();
        }

        System.out.println("World");

    }

    public static void finalMethod() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finally");
    }

}
