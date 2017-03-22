package ch03;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xuan on 2017/3/22 0022.
 */
public class ReenterLockCondition implements Runnable {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    @Override
    public void run() {
        lock.lock();
        try {
            System.out.println("happy start");
            condition.await();
            System.out.println(Thread.currentThread().getName() + " done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ReenterLockCondition(), "hello");
        thread.start();
        Thread.sleep(300);
        System.out.println("main try to signal the happy thread");
        lock.lock();
        condition.signal();
        System.out.println("main done");
        lock.unlock();
    }
}
