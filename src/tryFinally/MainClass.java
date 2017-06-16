package tryFinally;

import practice.java.kranthi.ThreadColor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * project intelliJworkspace Created by kranthikumarpolimetla in package ${PACKAGE_NAME} on 6/12/17.
 */
public class MainClass {
    static final String EOF = "EOF";
    private static MyProducer myProducer;
   private static MyConsumer myConsumer;
    private static MyConsumer myConsumer1;
    private static ReentrantLock bufferedLock = new ReentrantLock();

    public static void main(String[] args) {
        List<String> buffer = new ArrayList<>();

         myProducer = new MyProducer(buffer, ThreadColor.ANSI_BLUE, bufferedLock);
         myConsumer = new MyConsumer(buffer, ThreadColor.ANSI_CYAN, bufferedLock);
         myConsumer1 = new MyConsumer(buffer, ThreadColor.ANSI_GREEN, bufferedLock);

         startThreads();
    }
    private static void startThreads() {
        (new Thread(myProducer)).start();
        (new Thread(myConsumer)).start();
        (new Thread(myConsumer1)).start();
    }
}
