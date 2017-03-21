/**
 * Created by xuan on 2017/3/21 0021.
 */
public class JoinMain {
    private static int i;
    public static class AddThread extends Thread {
        @Override
        public void run() {
            while (i < 1000) {
                i++;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread at = new AddThread();
        at.start();
//        at.join();
        System.out.println(i);
    }
}
