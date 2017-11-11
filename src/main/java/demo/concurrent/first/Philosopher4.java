package demo.concurrent.first;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/11/11 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class Philosopher4 extends Thread {

    private boolean eating;
    private Philosopher4 left;
    private Philosopher4 right;
    private ReentrantLock table;
    private Condition condition;
    private Random random;

    public Philosopher4(ReentrantLock table) {
        this.eating = false;
        this.table = table;
        this.condition = table.newCondition();
        this.random = new Random();
    }

    public void setLeft(Philosopher4 left) {
        this.left = left;
    }

    public void setRight(Philosopher4 right) {
        this.right = right;
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();
                eat();
            }
        } catch (InterruptedException e) {
            // ...
        }
    }

    private void think() throws InterruptedException {
        this.table.lock();
        try {
            this.eating = false;
            this.left.condition.signal();
            this.right.condition.signal();
        } finally {
            table.unlock();
        }
        Thread.sleep(1000);
    }

    private void eat() throws InterruptedException {
        this.table.lock();
        try {
            while (left.eating || right.eating) {
                this.condition.await();
            }
            this.eating = true;
        } finally {
            this.table.unlock();
        }
        Thread.sleep(1000);
    }
}
