package reEntrantLock;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import static reEntrantLock.MainClass.EOF;

/**
 * project intelliJworkspace Created by kranthikumarpolimetla in package ${PACKAGE_NAME} on 6/12/17.
 */
public class MyConsumer implements Runnable {
    private String color;
    private final List<String> buffer;
    private ReentrantLock bufferLock;

    MyConsumer(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.color = color;
        this.buffer = buffer;
        this.bufferLock = bufferLock;
    }

    @Override
    public void run() {
        while (true) {
            bufferLock.lock();
            if (buffer.isEmpty()) {
                bufferLock.unlock();
                continue;
            }
            if (buffer.get(0).equals(EOF)) {
                bufferLock.unlock();
                System.out.println(color + "reached end of the file");
                break;
            } else {
                System.out.println(color + "removed " + buffer.remove(0));
            }
            bufferLock.unlock();
        }
    }
}
