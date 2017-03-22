package ch03;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xuan on 2017/3/22 0022.
 */
public class CountDownLatchDemo implements Runnable {
    private static CountDownLatch latch = new CountDownLatch(10);
    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(10)*1000);
            System.out.println(Thread.currentThread().getName() + " check done");
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            exec.submit(new CountDownLatchDemo());
        }
        exec.shutdown();
        latch.await();
        System.out.println("Fire");
    }
}
