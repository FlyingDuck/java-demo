package demo.concurrent.first;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/11/11 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class Philosopher3 extends Thread {
    private ReentrantLock leftChopstick;
    private ReentrantLock rightChopstick;
    private Random random;

    public Philosopher3(ReentrantLock leftChopstick, ReentrantLock rightChopstick) {
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
        random = new Random();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(random.nextInt(1000));  // 思考一会儿
                leftChopstick.lock();
                try {
                    // 获取右手边的筷子
                    if (rightChopstick.tryLock(1000, TimeUnit.MILLISECONDS)) {
                        try {
                            Thread.sleep(random.nextInt(1000));
                        } finally {
                            rightChopstick.unlock();
                        }
                    } else {
                        // 没有获取到右手边的筷子，放弃并继续思考
                    }
                } finally {
                    leftChopstick.unlock();
                }
            }
        } catch (InterruptedException e) {
            // ...
        }
    }
}
