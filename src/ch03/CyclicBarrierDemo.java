package ch03;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by xuan on 17-3-22.
 */
public class CyclicBarrierDemo {
    public static class Soldier implements Runnable {
        private String soldier;
        private final CyclicBarrier cyclic;

        public Soldier(String soldierName, CyclicBarrier cyclic) {
            this.soldier = soldierName;
            this.cyclic = cyclic;
        }

        @Override
        public void run() {
            try {
                cyclic.await();
                while (true) {
                    doWork();
                    cyclic.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        private void doWork() {
            try {
                Thread.sleep(new Random().nextInt(10)*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(soldier + ": work done");
        }
    }

    public static class BarrierRun implements Runnable {
        boolean flag;
        int N;

        public BarrierRun(boolean flag, int N) {
            this.flag = flag;
            this.N = N;
        }

        @Override
        public void run() {
            if (flag) {
                System.out.println("officer:[soldier " + N + " work done]");
            } else {
                System.out.println("officer:[soldier " + N + " gathered around]");
                flag = true;
            }
        }
    }

    public static void main(String[] args) {
        final int N = 10;
        Thread[] threads = new Thread[N];
        boolean flag = false;

        CyclicBarrier cylic = new CyclicBarrier(N, new BarrierRun(flag, N));
        System.out.println("gather around");
        for (int i = 0; i < N; i++) {
            System.out.println("soldier " + i + " here");
            threads[i] = new Thread(new Soldier("sodier " + i, cylic));
            threads[i].start();
        }
    }
}
