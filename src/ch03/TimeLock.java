package ch03;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xuan on 2017/3/22 0022.
 */
public class TimeLock implements Runnable {
    private static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                Thread.sleep(6000);
                System.out.println(Thread.currentThread().getName() + " done");
            } else {
                System.out.println(Thread.currentThread().getName() + " lock failed");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread())
                lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new TimeLock());
        Thread thread2 = new Thread(new TimeLock());
        thread1.setName("lucky");
        thread2.setName("unhappy");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("done");
    }
}
