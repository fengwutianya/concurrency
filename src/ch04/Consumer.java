package ch04;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by xuan on 2017/3/27 0027.
 */
public class Consumer implements Runnable {
    private static final int SLEEPTIME = 1000;
    private BlockingQueue<PCData> queue;

    public Consumer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("start consumer: " + Thread.currentThread().getId());
        Random random = new Random();
        try {
            while (true) {
                PCData data = queue.take();
                int re = data.getIntData()*data.getIntData();
                System.out.println(MessageFormat.format("{0}*{1}={2}", data.getIntData(),
                        data.getIntData(), re));
                Thread.sleep(random.nextInt(SLEEPTIME));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
