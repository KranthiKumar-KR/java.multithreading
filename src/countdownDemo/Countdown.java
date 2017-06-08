package countdownDemo;

import practice.java.kranthi.ThreadColor;


class Countdown {
    private int i;

    void doCountdown() {
        String color;

        switch (Thread.currentThread().getName()) {
            case "Thread-0":
                color = ThreadColor.ANSI_BLUE;
                break;
            case "Thread-1":
                color = ThreadColor.ANSI_CYAN;
                break;
            default:
                color = ThreadColor.ANSI_GREEN;
        }
/*


here int i is local variable to every thread, so it will be stored in the thread stack, so every thread has its own i value. so each thread will run 10 times
but if 'i' is not a local variable (instance variable ) then things will be different.


        for (int i = 0; i < 10; i++) {
            System.out.println(color + Thread.currentThread().getName() +" I:" +i);
        }*/

//if 'i' is a instance variable then it will be stored inside the heap memory of the application process, since threads wont create duplicate objects to target object
        //(they just keep copy of the reference to the object), so they share same object (same process heap).
        // in this case both the threads will be in a race to access the 'i' inside the heap memory. this race condition is called THREAD INTERRUPTION.
        // to avoid this thread interruption the temporary solution is to create two different objects of counter class and pass them separately to the two threads.
        // in this way we are giving two different objects to the two threads, which wont happen automatically.
        // BUT THIS IS NOT AT ALL ADVISABLE... DEVELOPERS WONT DO IT THIS WAY

        //for proper solution and further explanation, see the main class.


        /**
         * sychronized method is used to achieve thread concurrency, which is the best and the suggested way
         * if we synchronize a method or a block of a code, then the next thread will wait until the current thread finishes its job with the sychronized block
         * but we have to be careful while synchronizing code. because we should only synchronize the code the really needs synchronization
         * or code that may cause thread interference.
         * in this case this for loop is causing thread interference. so we synchronized it.
         * sometimes we may need to synchronize a complete class or a method or a variable or a static block or a instance block
         **/
        synchronized (this) {
        for (i = 0; i < 10; i++) {
            System.out.println(color + Thread.currentThread().getName() + " I:" + i);
        }
     }
    }
}
