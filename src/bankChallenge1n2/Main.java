package bankChallenge1n2;


/**
 * project intelliJworkspace Created by kranthikumarpolimetla in package bankChallenge1n2 on 6/26/17.
 */
public class Main {
    public static void main(String[] args) {
        BankAccount kranthi = new BankAccount("1234-56", 1000.00);


        /**
         * thread safety with synchronization keywRord
         */

        Thread trans1 = new Thread(() -> {
            kranthi.deposit(300.00);
            System.out.println("amount deposited $300.00 and final balance is: $" + kranthi.getBalance());

            kranthi.withdraw(50.00);
            System.out.println("amount withdrawn $50.00 and final balance is: $" + kranthi.getBalance());
        });

        Thread trans2 = new Thread(() -> {
            kranthi.deposit(203.75);
            System.out.println("amount deposited $203.75 and final balance is: $" + kranthi.getBalance());

            kranthi.withdraw(100.00);
            System.out.println("amount withdrawn $100.00 and final balance is: $" + kranthi.getBalance());
        });

        trans1.start();
        trans2.start();

    }
}
