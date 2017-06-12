package DeadlockWaitNotify;

/**
 * Created by kranthikumarpolimetla on 6/9/17.
 */
public class Message {

    String message;
    boolean empty = true;

    public synchronized String read() {
        while (empty) {
            try {
                wait(); // when a thread calls wait method it will suspend execution and release whatever locks it is holding
                //until another thread issues a notification that something important has happened.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        empty = true;
        notifyAll(); // notifies all threads that are holding locks on to the current thread.
        return message;
    }

    public synchronized void write(String message) {
        while(!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        empty = false;
        this.message = message;
        notifyAll();

    }
}
