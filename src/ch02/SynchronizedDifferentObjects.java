package ch02;

/**
 * Created by xuan on 17-3-21.
 */
public class SynchronizedDifferentObjects implements Runnable {
    private static int i = 0;

    /*
    static synchronized lock the Class
    synchronized lock the object
     */
    private synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 111111111; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new SynchronizedDifferentObjects());
        Thread t2 = new Thread(new SynchronizedDifferentObjects());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
