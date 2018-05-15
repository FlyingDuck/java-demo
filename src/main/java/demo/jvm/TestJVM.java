package demo.jvm;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Bennett Dong <br>
 * Date : 2018/5/15 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:  <a>https://mp.weixin.qq.com/s/aLMJkbRAgTDwYV8GO0psQg</a>
 */
public class TestJVM {

    public static void main(String[] args) throws InterruptedException {
        final int count = 500;

        final CountDownLatch latch = new CountDownLatch(count);
        final TestThread[] threads = new TestThread[count];
        for (int i=0; i<count; i++) {
            threads[i] = new TestThread(latch);
            threads[i].start();
        }

        TimeUnit.MILLISECONDS.sleep(300);

        for (int i=0; i<count; i++) {
            threads[i].setStop(true);
        }

    }


    public static class TestThread extends Thread {

        CountDownLatch latch;
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        boolean stop = false;

        public TestThread(CountDownLatch latch) {
            this.latch = latch;
        }

        public boolean isStop() {
            return this.stop;
        }

        public void setStop(boolean stop) {
            this.stop = stop;
        }

        @Override
        public void run() {
            while (!stop) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                byte[] buf = new byte[0];
            }
        }
    }

}
