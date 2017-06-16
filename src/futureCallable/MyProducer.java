package futureCallable;


import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * project intelliJworkspace Created by kranthikumarpolimetla in package ${PACKAGE_NAME} on 6/12/17.
 */
public class MyProducer implements Runnable {
    private final List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;


    MyProducer(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] nums = {"1", "2", "3", "4", "5"};
        for (String num : nums) {
            try {
                System.out.println(color + " adding numbers: " + num);
                bufferLock.lock();
                buffer.add(num);
                bufferLock.unlock();
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                System.out.println(color + "producer interrupted");
            }
        }
        bufferLock.lock();
        buffer.add("EOF");
        bufferLock.unlock();
    }
}
