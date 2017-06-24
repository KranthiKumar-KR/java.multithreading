package threadStarvation;

/**
 * project intelliJworkspace Created by kranthikumarpolimetla in package threadStarvation on 6/22/17.
 */
public class Threads implements Runnable {

    private String color;
    private int counter = 1;

    public Threads(String color) {
        this.color = color;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            synchronized (ThreadStarvationMain.lock) {
                System.out.format(color + "%s : runcount %d \n", Thread.currentThread().getName(), counter++);
            }
        }
    }
}
