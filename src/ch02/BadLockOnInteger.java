package ch02;

/**
 * Created by xuan on 17-3-21.
 */
public class BadLockOnInteger implements Runnable {
    private static Integer i = 0;
    private static BadLockOnInteger  instance = new BadLockOnInteger();

    private void increase() {
        for (int j = 0; j < 10000; j++) {
            synchronized (i) {
                i++;
            }
        }
    }

    @Override
    public void run() {
        increase();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();t2.start();
        t1.join();t2.join();
        System.out.println(i);
    }
}
