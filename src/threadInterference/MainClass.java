package threadInterference;

import practice.java.kranthi.ThreadColor;

import java.util.ArrayList;
import java.util.List;

/**
 * project intelliJworkspace Created by kranthikumarpolimetla in package ${PACKAGE_NAME} on 6/12/17.
 */
public class MainClass {
     static final String EOF = "EOF";

    public static void main(String[] args) {
        List<String> buffer =new ArrayList<>();

        MyProducer myProducer = new MyProducer(buffer, ThreadColor.ANSI_BLUE);
        MyConsumer myConsumer = new MyConsumer(buffer, ThreadColor.ANSI_CYAN);
        MyConsumer myConsumer1 = new MyConsumer(buffer, ThreadColor.ANSI_GREEN);


        (new Thread(myProducer)).start();
         (new Thread(myConsumer)).start();
         (new Thread(myConsumer1)).start();

    }
}
