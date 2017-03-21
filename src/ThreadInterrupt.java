/**
 * Created by xuan on 2017/3/21 0021.
 */
public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            int i = 0;
            while (true) {
//                if (Thread.currentThread().isInterrupted()) {
//                    System.out.println("Interrupted!" + i);
//                    break;
//                }
                Thread.yield();
                i++;
            }
        }
    });
        thread.setDaemon(true);
        thread.start();

            Thread.sleep(1000);
            thread.interrupt();

    }

}
