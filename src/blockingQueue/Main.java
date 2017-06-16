package blockingQueue;

import practice.java.kranthi.ThreadColor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * project intelliJworkspace Created by kranthikumarpolimetla in package blockingQueue on 6/16/17.
 */
public class Main {
    public static final String EOF = "EOF";
    private static ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<>(6); //we need to define the capacity of ArrayBlockingQueue initially
    private static MyProducer myProducer;
    private static MyConsumer myConsumer1;
    private static MyConsumer myConsumer2;

    public static void main(String[] args) {
        runThreads();

        ExecutorService executorService = Executors.newFixedThreadPool(3); //fixed no of threads in the thread pool, 3

        executorService.execute(myProducer);
        executorService.execute(myConsumer1);
        executorService.execute(myConsumer2);

        executorService.shutdown(); // shutting down the executor service is mandatory

    }

    private static void runThreads() {
        myProducer = new MyProducer(buffer, ThreadColor.ANSI_CYAN);
        myConsumer1 = new MyConsumer(ThreadColor.ANSI_GREEN, buffer);
        myConsumer2 = new MyConsumer(ThreadColor.ANSI_BLUE, buffer);
    }
}
