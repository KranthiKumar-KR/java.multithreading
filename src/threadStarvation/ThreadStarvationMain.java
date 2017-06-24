package threadStarvation;

import practice.java.kranthi.ThreadColor;

import java.util.concurrent.locks.ReentrantLock;

/**
 * project intelliJworkspace Created by kranthikumarpolimetla in package threadStarvation on 6/22/17.
 */
public class ThreadStarvationMain {
    //public static final Object lock = new Object();
    public static final ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {
//        Thread thread1 = new Thread(new Threads(ThreadColor.ANSI_BLUE), "Thread priority 10");
//        Thread thread2 = new Thread(new Threads(ThreadColor.ANSI_GREEN), "Thread priority 8");
//        Thread thread3 = new Thread(new Threads(ThreadColor.ANSI_CYAN), "Thread priority 6");
//        Thread thread4 = new Thread(new Threads(ThreadColor.ANSI_PURPLE), "Thread priority 4");
//        Thread thread5 = new Thread(new Threads(ThreadColor.ANSI_RED), "Thread priority 2");

        Thread thread1 = new Thread(new ThreadsFairlock(ThreadColor.ANSI_BLUE), "Thread priority 10");
        Thread thread2 = new Thread(new ThreadsFairlock(ThreadColor.ANSI_GREEN), "Thread priority 8");
        Thread thread3 = new Thread(new ThreadsFairlock(ThreadColor.ANSI_CYAN), "Thread priority 6");
        Thread thread4 = new Thread(new ThreadsFairlock(ThreadColor.ANSI_PURPLE), "Thread priority 4");
        Thread thread5 = new Thread(new ThreadsFairlock(ThreadColor.ANSI_RED), "Thread priority 2");


        thread1.setPriority(10);
        thread2.setPriority(8);
        thread3.setPriority(6);
        thread4.setPriority(4);
        thread5.setPriority(2);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();


    }
}
