package threadInterference;

import practice.java.kranthi.ThreadColor;

import java.util.ArrayList;
import java.util.List;

/**
 * project intelliJworkspace Created by kranthikumarpolimetla in package ${PACKAGE_NAME} on 6/12/17.
 */
public class MainClass {
     static final String EOF = "EOF";

    public static void main(String[] args) {
        List<String> buffer =new ArrayList<>();

        MyProducer myProducer = new MyProducer(buffer, ThreadColor.ANSI_BLUE);
        MyConsumer myConsumer = new MyConsumer(buffer, ThreadColor.ANSI_CYAN);
        MyConsumer myConsumer1 = new MyConsumer(buffer, ThreadColor.ANSI_GREEN);


        (new Thread(myProducer)).start();
         (new Thread(myConsumer)).start();
         (new Thread(myConsumer1)).start();

         //the main disadvantages of synchronized block are
        // 1. threads that are blocked waiting to execute the synchronized code can't be interrupted
        // once they are blocked they are stuck there until they get the lock for the object the code is synchronizing on
        // 2. synchronized block must be with in the same method, in other words we can't start a synchronized block in one method and end that in another method
         // 3. we cant test to see if an object's intrinsic lock is available or find out any other information about that lock. also if the lock is not available
        // we can't timeout after waiting for certain period of time.
        // if we reach the beginning of the synchronized block we can either get the lock and continue executing or block at the line of the code until we get the lock.
        // 4. if multiple threads are waiting for the lock, its not first come first serve, jvm gets to decide which thread gets the lock first. in other words the thread that
        // first blocked the lock may get the lock at the end and vice versa.

        // to overcome these disadvantages it is better to use java.util.concurrent.lock package classes to solve concurrency issues.

    }
}
