package deadlocksExamplesAndPreventions;

/**
 * project intelliJworkspace Created by kranthikumarpolimetla in package deadlocksExamplesAndPreventions on 6/16/17.
 */
public class DeadlockExample1 {
    static Object lock1 = new Object();
    static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread1().start();
        new Thread2().start();

    }

    private static class Thread1 extends Thread {
        public void run() {
            synchronized (lock1) {
                System.out.println("thread1 has lock1 " +currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("thread1 waiting for lock2 " +currentThread().getName());

                synchronized (lock2) {
                    System.out.println("thread1 has both lock1 and lock2 " +currentThread().getName());

                }
                System.out.println("thread1 released lock2 " +currentThread().getName());
            }
            System.out.println("thread1 released lock1. Exiting...... " +currentThread().getName());
        }
    }

    private static class Thread2 extends Thread {
        public void run() {
            synchronized (lock2) {
                System.out.println("thread2 has lock2 " +currentThread().getName());

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

                System.out.println("thread2 waiting for lock1 " +currentThread().getName());

                synchronized (lock1) {
                    System.out.println("thread2 has both lock2 and lock1 " +currentThread().getName());

                }
                System.out.println("thread2 released lock1 " +currentThread().getName());
            }
            System.out.println("thread2 has released lock2, exiting...... " +currentThread().getName());
        }
    }
}
