package demo.concurrent.first;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/11/11 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class Interruptible {

    public static void main(String[] args) {
        final ReentrantLock lock1 = new ReentrantLock();
        final ReentrantLock lock2 = new ReentrantLock();

        Thread t1 = new Thread(){
            @Override
            public void run() {
                try {
                    lock1.lockInterruptibly();
                    Thread.sleep(1000);
                    lock2.lockInterruptibly();
                } catch (InterruptedException e) {
                    System.out.println("Thread-1 interrupted");
                }
            }
        };

        // ...
    }
}
