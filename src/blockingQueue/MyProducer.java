package blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;

import static blockingQueue.Main.EOF;

/**
 * project intelliJworkspace Created by kranthikumarpolimetla in package blockingQueue on 6/16/17.
 */
public class MyProducer implements Runnable {

    private ArrayBlockingQueue<String> buffer;
    private String color;

    public MyProducer(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }


    @Override
    public void run() {
        String[] nums = {"1", "2", "3", "4", "5"};

        for (String num : nums) {
            try {
                System.out.println(color +"adding number..." + num);
                buffer.put(num); // here we don't need any ReentrantLock or Synchronized block since put() itself is a thread safe method
            } catch (InterruptedException e) {
                System.out.println(color +"error while adding number " + num); // since put() method is thread safe there will be chances of throwing
                // interruption errors when some other threads are trying to access the put method at the same time.
            }
        }

        try {
            System.out.println(color +"adding end of the file EOF");
            buffer.put(EOF); // here we don't need any ReentrantLock or Synchronized block since put() itself is a thread safe method
        } catch (InterruptedException e) {
            System.out.println(color +"error while adding EOF"); // since put() method is thread safe there will be chances of throwing
            // interruption errors when some other threads are trying to access the put method at the same time.
        }
    }
}
