package ch03;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xuan on 2017/3/22 0022.
 */
public class InterptLock implements Runnable {
    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();
    private int i = 0;

    public InterptLock(int i) {
        this.i = i;
    }
    @Override
    public void run() {
        if (i == 1) {
            try {
                lock1.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock1.unlock();
            }
            try {
                lock2.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread1 done");
        }
        if (i == 2) {
            try {
                lock2.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock2.unlock();
            }
            try {
                lock1.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new InterptLock(1));
        Thread thread2 = new Thread(new InterptLock(2));
        thread1.start();
        thread2.start();
        Thread.sleep(1000);
        thread2.interrupt();
        thread1.join();
        thread2.join();
        System.out.println("done");
    }
}
