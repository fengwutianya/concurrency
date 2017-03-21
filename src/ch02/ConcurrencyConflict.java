package ch02;

/**
 * Created by xuan on 17-3-21.
 */
public class ConcurrencyConflict implements Runnable {
    private static ConcurrencyConflict cc = new ConcurrencyConflict();
    private static volatile int i = 0;

    private static void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 11111111; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(cc);
        Thread t2 = new Thread(cc);
        t1.start();t2.start();
        t1.join();t2.join();
        System.out.println(i);
    }
}
