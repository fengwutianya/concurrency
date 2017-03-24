package ch02;

/**
 * there is a deadlock because out is a static final PrintStream,
 * and println is a synchronized  method
 * Created by xuan on 2017/3/24 0024.
 */
public class SuspendAndResume {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.print("a");
                }
            }
        });
        thread.start();
        Thread.sleep(10);
        thread.suspend();
        System.out.println("b");
        thread.resume();
    }
}
