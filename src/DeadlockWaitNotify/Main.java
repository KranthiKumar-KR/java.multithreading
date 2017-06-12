package DeadlockWaitNotify;

/**
 * Created by kranthikumarpolimetla on 6/9/17.
 */
public class Main {

    public static void main(String[] args) {
        Message message = new Message();
        new Thread(new WriteMessage(message)).start();
        new Thread(new ReadMessage(message)).start();
    }
}
