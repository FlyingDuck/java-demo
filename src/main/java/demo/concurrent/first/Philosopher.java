package demo.concurrent.first;

import java.util.Random;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/11/11 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc: 多把锁 实现哲学家行为
 */
public class Philosopher extends Thread {
    private Chopstick left;
    private Chopstick right;
    private Random random;

    public Philosopher(Chopstick left, Chopstick right) {
        this.left = left;
        this.right = right;
        random = new Random();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(random.nextInt(1000));  // 思考一会儿
                synchronized (left) {                       // 拿起左手的筷子
                    synchronized (right) {                  // 拿起右手的筷子
                        Thread.sleep(random.nextInt(1000)); // 进餐
                    }
                }
            }
        } catch (InterruptedException e) {
            // handle exception
        }
    }
}
