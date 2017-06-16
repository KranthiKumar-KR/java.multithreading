package tryFinally;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import static tryFinally.MainClass.EOF;

/**
 * project intelliJworkspace Created by kranthikumarpolimetla in package ${PACKAGE_NAME} on 6/12/17.
 */
public class MyConsumer implements Runnable {
    private String color;
    private final List<String> buffer;
    private ReentrantLock bufferedLock;

    MyConsumer(List<String> buffer, String color, ReentrantLock bufferedLock) {
        this.color = color;
        this.buffer = buffer;
        this.bufferedLock = bufferedLock;
    }


    /**
     * we can actually check if the lock is available or not using tryLock() method
     * if the lock is available it will block the lock
     * if the lock is not available a piece of code will be executed
     * in the below example if the lock is available the code inside if() block will be executed,
     * if the lock is not available the code in the else() will be executed
     * both codes in if and else blocks is up to us, we can execute whatever we want to
     */

    @Override
    public void run() {
        int counter = 0;
        while (true) {
            try {
                if (bufferedLock.tryLock(1, TimeUnit.NANOSECONDS)) { // two parameters passed to tryLock() are timeout and timeoutUnits
                    // it will check if we are able to obtain a lock within the specified time limit, else it will throw an exception
                    try {
                        if (buffer.isEmpty()) {
                            continue;
                        }
                        System.out.println(color + " count request: " +counter);
                        counter = 0;
                        if (buffer.get(0).equals(EOF)) {
                            System.out.println(color + "reached end of the file");
                            //bufferedLock.unlock(); we should not call unlock() inside the try block, we only need to call it in the corresponding finally block.
                            break;
                        } else {
                            System.out.println(color + "removed " + buffer.remove(0));
                        }
                    } finally{
                        bufferedLock.unlock();
                    }
                } else {
                    counter++;
                }
            } catch (InterruptedException e) {
                System.out.println("couldn't get the lock with in 100 micro seconds");            }

        }

        /*@Override
        public void run() {
            int counter = 0;
            while (true) {
                    if (bufferedLock.tryLock()) {
                        try {
                            if (buffer.isEmpty()) {
                                continue;
                            }
                            System.out.println(color + " count request: " +counter);
                            counter = 0;
                            if (buffer.get(0).equals(EOF)) {
                                System.out.println(color + "reached end of the file");
                                //bufferedLock.unlock(); we should not call unlock() inside the try block, we only need to call it in the corresponding finally block.
                                break;
                            } else {
                                System.out.println(color + "removed " + buffer.remove(0));
                            }
                        } finally{
                            bufferedLock.unlock();
                        }
                    } else {
                        counter++;
                    }
            }*/



    /*
      the main advantage of using try-finally blocks is:
      we need not care for unlocking the lock of thread every time we own it.
      we can lock it once and add all the code that needs synchronization in the try block
      and add the unlock in the finally block
      every time a line of code inside try block throws any error, the code in the finally block will be executed
      so every time we own the lock and we need to unlock it, finally block will take care of it.
      if we add unlock() inside the try block, it will throw a RTE saying illegal monitor state exception
      which means we are trying to unlock the object when we don't own the lock
      so we need to be careful about unlocking the object lock
     */
        /*while (true) {
            bufferedLock.lock();
            try {
                if (buffer.isEmpty()) {
                    continue;
                }
                if (buffer.get(0).equals(EOF)) {
                    System.out.println(color + "reached end of the file");
                    //bufferedLock.unlock(); we should not call unlock() inside the try block, we only need to call it in the corresponding finally block.
                    break;
                } else {
                    System.out.println(color + "removed " + buffer.remove(0));
                }
            } finally {
                bufferedLock.unlock();
            }
        }*/

    }
}
