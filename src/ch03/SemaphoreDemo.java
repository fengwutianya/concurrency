package ch03;

import java.util.concurrent.Semaphore;

/**
 * 信号量 semaphore acquire() release()
 * Created by xuan on 2017/3/22 0022.
 */
public class SemaphoreDemo implements Runnable {
    private static Semaphore smph = new Semaphore(5);

    @Override
    public void run() {
        try {
            smph.acquire();
            System.out.println(Thread.currentThread().getName() + " started");
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + " done");
            smph.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(new SemaphoreDemo()).start();
        }
    }
}
