package bankChallenge1n2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * project intelliJworkspace Created by kranthikumarpolimetla in package bankChallenge1n2 on 6/26/17.
 */
 public class BankAccount {
    private String accountNo;
    private double balance;
    private Lock lock;

    BankAccount(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
        this.lock = new ReentrantLock();
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void printAccountNo() {
        System.out.println("Account number is: " + accountNo);
    }

    double getBalance() {
        return balance;
    }

    void deposit(double amount) {
        boolean status = false;
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                try {
                    balance += amount;
                    status = true;
                } finally {
                    lock.unlock();
                }
            } else  {
                System.out.println("failed to obtain the lock with in 1 second");
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("status of the transaction is: " +status);

       /* lock.lock();
        try {
            balance += amount;
        } finally {
            lock.unlock();
        }*/

       /* synchronized (this) {
            balance += amount;
        }*/
    }

    void withdraw(double amount) {
        boolean status = false;
        try {
            if(lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                try{
                    balance -= amount;
                    status = true;
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("failed to obtain lock with in 1 second");
            }
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("status of the transaction is: " +status);

       /* lock.lock();
        try {
            balance += amount;
        } finally {
            lock.unlock();
        }*/

        /*synchronized (this) {
            balance -= amount;
        }*/
    }
}
