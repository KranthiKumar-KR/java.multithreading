package executorService;

import practice.java.kranthi.ThreadColor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * project intelliJworkspace Created by kranthikumarpolimetla in package ${PACKAGE_NAME} on 6/12/17.
 */
public class MainClass {
    static final String EOF = "EOF";
    private static ReentrantLock bufferLock = new ReentrantLock();
    //the disadvantage of using reEntrantLock is that we have to manually close the reEntrantLock
    //check myconsumer and myproducer classes how we manually lock and unlock the reentrantlock

    public static void main(String[] args) {
        List<String> buffer = new ArrayList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(3);


        MyProducer myProducer = new MyProducer(buffer, ThreadColor.ANSI_BLUE, bufferLock);
        MyConsumer myConsumer = new MyConsumer(buffer, ThreadColor.ANSI_CYAN, bufferLock);
        MyConsumer myConsumer1 = new MyConsumer(buffer, ThreadColor.ANSI_GREEN, bufferLock);


        executorService.execute(myProducer);
        executorService.execute(myConsumer);
        executorService.execute(myConsumer1);

        executorService.shutdown();

    }
}
