package deadlocksExamplesAndPreventions;


/**
 * project intelliJworkspace Created by kranthikumarpolimetla in package deadlocksExamplesAndPreventions on 6/16/17.
 */
public class DeadlockExample1Prevention {
    final static Object lock3 = new Object();
    final static Object lock4 = new Object();

    public static void main(String[] args) {
        new Thread3().start();
        new Thread4().start();

    }

    /**
     * here instead of trying to obtain locks in different order, we are obtaining locks in same order.
     * which makes the other threads wait for the locks until the current thread releases the locks it is holding
     */

    private static class Thread3 extends Thread {
        public void run() {
            synchronized (lock3) {
                System.out.println("thread1 has lock1 ");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("thread1 waiting for lock2 ");

                synchronized (lock4) {
                    System.out.println("thread1 has both lock1 and lock2 ");

                }
                System.out.println("thread1 released lock2 " );
            }
            System.out.println("thread1 released lock1. Exiting...... " );
        }
    }

    private static class Thread4 extends Thread {
        public void run() {
            synchronized (lock3) {
                System.out.println("thread2 has lock2 ");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

                System.out.println("thread2 waiting for lock1 ");

                synchronized (lock4) {
                    System.out.println("thread2 has both lock2 and lock1 ");

                }
                System.out.println("thread2 released lock1 ");
            }
            System.out.println("thread2 has released lock2, exiting...... ");
        }
    }
}
