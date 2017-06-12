package DeadlockWaitNotify;


public class ReadMessage implements Runnable {
    private Message message;

     ReadMessage(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        for (String latestMessage = message.read(); !latestMessage.equals("finished"); latestMessage = message.read()) {
            System.out.println(latestMessage);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
