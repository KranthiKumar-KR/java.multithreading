package joinInterrupt;


public class Main {

    public static void main(String [] args) {

        // this will create thread interruption in case of instance variables as explained in the CountdownThread class
        // to avoid this thread interruption we go with a temporary and non-recommended solution of passing two different objects of countdown class after this commented code
        /*        Countdown countdown = new Countdown();

        CountdownThread countdownThread = new CountdownThread(countdown);
        CountdownThread countdownThread2 = new CountdownThread(countdown);

        countdownThread.start();
        countdownThread2.start();*/

        // passing two different objects of countdown class
        /**
         * instead of passing of two different objects of countdown class, we can achieve concurrency buy synchronizing the required blocks of code
         * see the code after this commented out code of passing two different objects
         */


        /*Countdown countdown1 = new Countdown();
        Countdown countdown2 = new Countdown();

        CountdownThread countdownThread1 = new CountdownThread(countdown1);
        CountdownThread countdownThread2 = new CountdownThread(countdown2);

        countdownThread1.start();
        countdownThread2.start();
        */

        Countdown countdown = new Countdown();

        CountdownThread countdownThread = new CountdownThread(countdown);
        CountdownThread countdownThread2 = new CountdownThread(countdown);

        countdownThread.start();
        countdownThread2.start();


    }
}
