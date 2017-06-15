package threadInterference;

import java.util.List;
import java.util.Random;

/**
 * project intelliJworkspace Created by kranthikumarpolimetla in package ${PACKAGE_NAME} on 6/12/17.
 */
public class MyProducer implements Runnable {
    private final List<String> buffer;
    private String color;

     MyProducer(List<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        Random random = new Random(2000);
        String[] nums = {"1", "2", "3", "4", "5"};
        for (String num: nums) {
                try {
                    System.out.println(color + " adding numbers: " + num);
                    synchronized (buffer) {
                        buffer.add(num);
                    }
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    System.out.println(color + "producer interrupted");
                }
        }
        synchronized (buffer) {
            buffer.add("EOF");
        }
    }
}
