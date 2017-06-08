package practice.java.kranthi;


public class Main {

    public static void main(String[] args) {
        System.out.println(ThreadColor.ANSI_BLUE +" this is hi from main thread");

       final  AnotherThread anotherThread = new AnotherThread();
        anotherThread.setName("kranthi");
        anotherThread.start();

        new Thread(() -> System.out.println(ThreadColor.ANSI_CYAN +" this is hi from anonymous inner class process of creating a new thread")).start();

       /* /**
         * this will interrupt a thread and interruption exception is thrown along with printed message we wrote
         *//*
        Thread myRunnableThread = new Thread(new MyRunnableImplementation() {
            @Override
            public void run() {
                System.out.println(ThreadColor.ANSI_RED +"this is hi from anonymous inner class implementation of runnable interface");

            }
        });
        myRunnableThread.start();
        anotherThread.interrupt(); //interrupts anotherThread */


        Thread myRunnableThread = new Thread(new MyRunnableImplementation() {
            @Override
            public void run() {
                System.out.println("this is hi from anonymous class implementation of runnable interface");

                try {
                    anotherThread.join(); // current thread will wait until anotherThread has completed execution, once anotherThread is completed current thread will resume functionality
                    System.out.println(ThreadColor.ANSI_RED +"another thread has timed out or interrupted so I am running again");
                } catch (InterruptedException e) {
                    System.out.println(ThreadColor.ANSI_RED +"i couldn't wait after all so I am running");
                }
            }
        });
        myRunnableThread.start();

        System.out.println(ThreadColor.ANSI_BLUE +"hello again from main thread");
    }
}
