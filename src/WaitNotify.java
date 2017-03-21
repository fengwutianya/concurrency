/**
 * Created by xuan on 2017/3/21 0021.
 */
public class WaitNotify {
    private static Object obj = new Object();
    public static class T1 extends Thread{
        @Override
        public void run() {
            synchronized (obj) {
                System.out.println(System.currentTimeMillis() + ": T1 start");
                try {
                    System.out.println(System.currentTimeMillis() + ": wait for obj");
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ": T1 end");
            }
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            synchronized (obj) {
                System.out.println(System.currentTimeMillis() + ": T2 start");
                obj.notify();   //只是唤醒一个线程，sleep并不释放锁，因此t1要等t2的同步块执行完重新获得锁之后才能执行
                System.out.println(System.currentTimeMillis() + ": T2 end");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        T1 t1 = new T1();
        T2 t2 = new T2();
        t1.start();
        t2.start();
    }
}
