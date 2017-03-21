/**
 * Created by xuan on 17-3-21.
 */
public class SyncConcurrencyConflict implements Runnable {
    private static SyncConcurrencyConflict scc = new SyncConcurrencyConflict();
    private static int i = 0;

    private static void increase() {
        synchronized (scc) {
            i++;
        }
//        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 11111111; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(scc);
        Thread t2 = new Thread(scc);
        t1.start();t2.start();
        t1.join();t2.join();
        System.out.println(i);
    }
}
