package ch03;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xuan on 2017/3/22 0022.
 */
public class TryLock implements Runnable {
    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();
    private int i;

    public TryLock(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        if (i == 1) {
            while (true) {
                if (lock1.tryLock()) {
                    try {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (lock2.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getName() + " done");
                                break;
                            } finally {
                                lock2.unlock();
                            }
                        }
                    } finally {
                        lock1.unlock();
                    }
                }
            }
        } else {
            while (true) {
                if (lock2.tryLock()) {
                    try {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (lock1.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getName() + " done");
                                break;
                            } finally {
                                lock1.unlock();
                            }
                        }
                    } finally {
                        lock2.unlock();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new TryLock(1));
        Thread thread2 = new Thread(new TryLock(2));
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(" done ");
    }
}
