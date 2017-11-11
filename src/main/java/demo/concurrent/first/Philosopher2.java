package demo.concurrent.first;

import java.util.Random;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/11/11 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class Philosopher2 extends Thread {
    private Chopstick first;
    private Chopstick second;
    private Random random;

    public Philosopher2(Chopstick left, Chopstick right) {
        if (left.getId() < right.getId()) {
            first = left;
            second = right;
        } else {
            first = right;
            second = left;
        }
        random = new Random();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(random.nextInt(1000));  // 思考一会儿
                synchronized (first) {                       // 拿起左手的筷子
                    synchronized (second) {                  // 拿起右手的筷子
                        Thread.sleep(random.nextInt(1000)); // 进餐
                    }
                }
            }
        } catch (InterruptedException e) {
            // handle exception
        }
    }
}
