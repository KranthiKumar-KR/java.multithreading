package threadStarvation;

/**
 * project intelliJworkspace Created by kranthikumarpolimetla in package threadStarvation on 6/24/17.
 */
public class ThreadsFairlock implements Runnable {
    private String color;
    private int counter = 1;

    public ThreadsFairlock(String color) {
        this.color = color;
    }


    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            ThreadStarvationMain.lock.lock();

            try {
                System.out.format(color + " %s : count = %d \n", Thread.currentThread().getName(), counter++);
            } finally {
                ThreadStarvationMain.lock.unlock();
            }
        }
    }
}
