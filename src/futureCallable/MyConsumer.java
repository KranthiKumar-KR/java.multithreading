package futureCallable;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import static futureCallable.MainClass.EOF;

/**
 * project intelliJworkspace Created by kranthikumarpolimetla in package ${PACKAGE_NAME} on 6/12/17.
 */
public class MyConsumer implements Runnable {
    private String color;
    private final List<String> buffer;
    private ReentrantLock bufferedLock;

    MyConsumer(List<String> buffer, String color, ReentrantLock bufferedLock) {
        this.color = color;
        this.buffer = buffer;
        this.bufferedLock = bufferedLock;
    }

    public void run() {
        int counter = 0;
        while (true) {
            if (bufferedLock.tryLock()) {
                try {
                    if (buffer.isEmpty()) {
                        continue;
                    }
                    System.out.println(color + " count request: " +counter);
                    counter = 0;
                    if (buffer.get(0).equals(EOF)) {
                        System.out.println(color + "reached end of the file");
                        //bufferedLock.unlock(); we should not call unlock() inside the try block, we only need to call it in the corresponding finally block.
                        break;
                    } else {
                        System.out.println(color + "removed " + buffer.remove(0));
                    }
                } finally{
                    bufferedLock.unlock();
                }
            } else {
                counter++;
            }
        }
    }
}
