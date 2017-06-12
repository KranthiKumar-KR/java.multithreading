package joinInterrupt;

public class CountdownThread extends Thread {

    private Countdown countdown;
     CountdownThread(Countdown countdown) {
        this.countdown = countdown;
    }

    @Override
    public void run() {
        countdown.doCountdown();
    }
}
