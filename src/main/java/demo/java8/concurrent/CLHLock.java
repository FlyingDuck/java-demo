package demo.java8.concurrent;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Bennett Dong <br>
 * Date : 2018/7/3 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class CLHLock {
    private AtomicReference<Node> tail;
    private ThreadLocal<Node> node;
    private ThreadLocal<Node> preNode;

    public CLHLock() {
        tail = new AtomicReference<>(new Node());
        node = new ThreadLocal<Node>(){
            @Override
            protected Node initialValue() {
                return new Node();
            }
        };

        preNode = new ThreadLocal<Node>(){
            @Override
            protected Node initialValue() {
                return null;
            }
        };
    }

    public void lock() {
        Node node = this.node.get();
        node.locked = true; // 获取锁
        Node pre = tail.getAndSet(node);
        this.preNode.set(pre);
        while (pre.locked) {
            // do nothing
        }
    }

    public void unlock() {
        Node node = this.node.get();
        node.locked = false;
        this.node.set(this.preNode.get());
    }


    static class  Node {
        private boolean locked;
    }


    public static void main(String[] args) throws InterruptedException {
        final CLHLock lock = new CLHLock();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    System.out.println(Thread.currentThread().getId() + " acquired the lock!");
                    lock.unlock();
                }
            }).start();
        }

        Thread.sleep(1000);
    }

}
