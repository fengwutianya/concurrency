package ch04;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xuan on 2017/3/27 0027.
 */
public class Producer implements Runnable {
    private static final int SLEEPTIME = 100;
    private boolean isRunning = true;
    private static AtomicInteger count = new AtomicInteger(0);
    private BlockingQueue<PCData> queue;

    public Producer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        PCData data = null;
        Random random = new Random();
        System.out.println("start producer: " + Thread.currentThread().getId());
        while (isRunning) {
            try {
                Thread.sleep(random.nextInt(SLEEPTIME));
                data = new PCData(count.incrementAndGet());
                if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                    System.err.println("failed to put data: " + data);
                } else {
                    System.out.println(data + " is put into queue");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop() {
        isRunning = false;
    }
}
