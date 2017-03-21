package ch02;

/**
 * Created by xuan on 2017/3/21 0021.
 */
public class ThreadSleep {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Interrupted!");
                        break;
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                    Thread.yield();
                }
            }
        });
        thread.start();
        Thread.sleep(20);
        thread.interrupt();
    }
}
