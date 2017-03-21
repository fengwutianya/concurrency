package ch03;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xuan on 17-3-21.
 */
public class DeadLock implements Runnable {
    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();
    private int lock;

    public DeadLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        if (lock == 1) {
            lock1.lock();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock2.lock();
        } else {
            lock2.lock();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock1.lock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new DeadLock(1));
        Thread thread2 = new Thread(new DeadLock(2));
        thread1.start();
        thread2.start();

        Thread.sleep(2000);
        System.out.println("Dead lock");
    }
}
