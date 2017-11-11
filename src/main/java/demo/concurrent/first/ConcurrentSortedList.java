package demo.concurrent.first;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/10/25 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc: 交替锁
 */
public class ConcurrentSortedList {  // 降序有序链表

    private class Node {
        int value;
        Node pre;
        Node next;

        ReentrantLock lock = new ReentrantLock();

        Node() {}

        Node(int value, Node pre, Node next) {
            this.value = value;
            this.pre = pre;
            this.next = next;
        }
    }

    private final Node head;
    private final Node tail;

    public ConcurrentSortedList() {
        this.head = new Node();
        this.tail = new Node();
        this.head.next = tail;
        this.tail.pre = head;
    }

    public void insert(int value) {
        Node current = this.head;
        current.lock.lock();
        Node next = current.next;
        try {
            while (true) {
                next.lock.lock();
                try {
                    if (next == tail || next.value < value) {
                        Node newNode = new Node(value, current, next);
                        next.pre = newNode;
                        current.next = newNode;
                        return;
                    }
                } finally {
                    current.lock.unlock();
                }
                current = next;
                next = current.next;

            }
        } finally {
            next.lock.unlock();
        }
    }

    public int size() {
        Node current = tail; // 这里为什么要是从尾部开始遍历呢？ 因为插入是从头部开始遍历的
        int count = 0;
        while (current != head) {
            ReentrantLock lock = current.lock;
            lock.lock();
            try {
                ++count;
                current = current.pre;
            } finally {
                lock.unlock();
            }
        }
        return count;
    }
}
