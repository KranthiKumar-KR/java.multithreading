package blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;

import static blockingQueue.Main.EOF;

/**
 * project intelliJworkspace Created by kranthikumarpolimetla in package blockingQueue on 6/16/17.
 */
public class MyConsumer implements Runnable {
    private String color;
    private final ArrayBlockingQueue<String> buffer;

    public MyConsumer(String color, ArrayBlockingQueue<String> buffer) {
        this.color = color;
        this.buffer = buffer;
    }


    @Override
    public void run() {
        while (true) {
           synchronized (buffer) {
               try {
                   if (buffer.isEmpty()) {
                       continue;
                   }
                   if (buffer.peek().equals(EOF)) { // here peek() method may throw a RTE NullPointerException when there is no next element,
                       // usually peek will look for the next element not the current element. when are at 0th index,
                       // the queue is not empty but there won't be any next element, which will throw an exception,
                       // that is why we need to need to enclose the code in synchronized() block.
                       System.out.println(color + " reached end of the file, exiting");
                       break;
                   } else {

                       System.out.println(color + " removing " + buffer.take()); // here don't we need any Reentrantlock or synchronized blocks since the take() method itself is a thread safe method
                   }
               } catch (InterruptedException e) {
                   System.out.println(e.getMessage());
               }
           }
        }
    }
}
