package ch03;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xuan on 17-3-21.
 */
public class ReenterLock implements Runnable {
    private static int i = 0;
    private static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        for (int j = 0; j < 100000; j++) {
            lock.lock();
            try {
                i++;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLock instance = new ReenterLock();
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();t2.start();
        t1.join();t2.join();
        System.out.println(i);
    }
}
