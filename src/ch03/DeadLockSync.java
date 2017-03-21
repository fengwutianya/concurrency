package ch03;

/**
 * Created by xuan on 17-3-21.
 */
public class DeadLockSync implements Runnable {
    private static Object obj1 = new Object();
    private static Object obj2 = new Object();
    private int lock;

    public DeadLockSync(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        if (lock == 1) {
            synchronized (obj1) {
                try {
                    Thread.sleep(500);
                    synchronized (obj2) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } else {
            synchronized (obj2) {
                try {
                    Thread.sleep(500);
                    synchronized (obj1) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new DeadLockSync(1));
        Thread t2 = new Thread(new DeadLockSync(2));
        t1.start();
        t2.start();
    }
}
