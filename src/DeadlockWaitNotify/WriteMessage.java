package DeadlockWaitNotify;

/**
 * Created by kranthikumarpolimetla on 6/9/17.
 */
public class WriteMessage implements Runnable {
    private Message message;

    public WriteMessage(Message message) {
        this.message = message;
    }

    @Override
    public void run() {

        String[] messages = {"hi i am kranthi", "i am from india", "i came here to do my masters", "now i am looking for new android developer job"};

        for (String message1 : messages) {
            message.write(message1);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
message.write("finished");

    }
}
