import java.util.ArrayList;

/**
 * Created by xuan on 17-3-21.
 */
public class ArrayListMultiThread {
    private static ArrayList<Integer> al = new ArrayList<>();

    public static class AddThread implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                al.add(i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AddThread());
        Thread t2 = new Thread(new AddThread());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(al.size());
    }
}
