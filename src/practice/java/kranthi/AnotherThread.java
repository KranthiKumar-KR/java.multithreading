package practice.java.kranthi;

/**
 * Created by kranthikumarpolimetla on 6/7/17.
 */
public class AnotherThread extends Thread {

    @Override
    public void run(){
        System.out.println(ThreadColor.ANSI_RED +" thread extended class, hi from  " +currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("current thread woke me up");
            //return;
        }

        System.out.println("3 seconds delay is completed " +currentThread().getName() + " is again running normally");
    }
}
