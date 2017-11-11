package demo.concurrent.first;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/11/11 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class Uninterruptible {

    public static void main(String[] args) throws InterruptedException {
        final Object o1 = new Object();
        final Object o2 = new Object();

        Thread t1 = new Thread(){
            @Override
            public void run() {
                try {
                    synchronized (o1) {
                        Thread.sleep(1000);
                        synchronized (o2) {}
                    }
                } catch (InterruptedException e) {
                    System.out.println("Thread-1 interrupted");
                }
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                try {
                    synchronized (o2) {
                        Thread.sleep(1000);
                        synchronized (o1) {}
                    }
                } catch (InterruptedException e) {
                    System.out.println("Thread-2 interrupted");
                }
            }
        };

        t1.start();
        t2.start();
        Thread.sleep(2000);
        t1.interrupt();
        t2.interrupt();
        t1.join();
        t2.join();
    }

}
