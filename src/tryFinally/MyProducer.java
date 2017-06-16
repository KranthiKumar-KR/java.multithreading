package tryFinally;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * project intelliJworkspace Created by kranthikumarpolimetla in package ${PACKAGE_NAME} on 6/12/17.
 */
public class MyProducer implements Runnable {
    private final List<String> buffer;
    private String color;
    ReentrantLock bufferedLock;

     MyProducer(List<String> buffer, String color, ReentrantLock bufferedLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferedLock = bufferedLock;
    }

    @Override
    public void run() {
        Random random = new Random(2000);
        String[] nums = {"1", "2", "3", "4", "5"};
        for (String num: nums) {
                try {
                    System.out.println(color + " adding numbers: " + num);
                    bufferedLock.lock();
                    try {
                        buffer.add(num);
                    } finally {
                        bufferedLock.unlock();
                    }
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    System.out.println(color + "producer interrupted");
                }
        }
        bufferedLock.lock();
        try {
            System.out.println("adding end of file EOF");
            buffer.add("EOF");
        } finally {
            bufferedLock.unlock();
        }
    }
}
