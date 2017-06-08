package practice.java.kranthi;

/**
 * Created by kranthikumarpolimetla on 6/8/17.
 */
public class MyRunnableImplementation implements Runnable {
    @Override
    public void run() {
        System.out.println(ThreadColor.ANSI_GREEN +"this is hi from runnable implementation version of creating mutliple threads");
    }
}
