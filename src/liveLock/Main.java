package liveLock;

/**
 * project intelliJworkspace Created by kranthikumarpolimetla in package liveLock on 6/24/17.
 */
public class Main {

    public static void main(String[] args) {

        final Worker worker1 = new Worker("worker1", true);
        final Worker worker2 = new Worker("worker2", true);

        final SharedPreference sharedPreference = new SharedPreference(worker1);

        new Thread(() ->
                worker1.work(sharedPreference, worker2)
        ).start();

        new Thread(() ->
                worker2.work(sharedPreference, worker1)
        ).start();

        //here both the threads are trying to give the ownership to others and waiting the other thread to complete their work.
        //hence both the threads will never get their locks and they will never finish
        //they are not in deadlock but they are in liveLock since both the threads are live and are locked with each other.
        //this can be prevented by changing the order of lock obtaining
    }
}
