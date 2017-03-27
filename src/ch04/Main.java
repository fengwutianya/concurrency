package ch04;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by xuan on 2017/3/27 0027.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<PCData> queue = new LinkedBlockingQueue<>();
        Producer p1 = new Producer(queue);
        Producer p2 = new Producer(queue);
        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);
        Consumer c3 = new Consumer(queue);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(c1);
        exec.execute(c2);
        exec.execute(c3);
        exec.execute(p1);
        exec.execute(p2);

        Thread.sleep(5*1000);
        p1.stop();
        p2.stop();
        Thread.sleep(3000);
        exec.shutdown();
    }
}
