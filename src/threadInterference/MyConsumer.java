package threadInterference;

import java.util.List;

import static threadInterference.MainClass.EOF;

/**
 * project intelliJworkspace Created by kranthikumarpolimetla in package ${PACKAGE_NAME} on 6/12/17.
 */
public class MyConsumer implements Runnable {
     private String color;
    private final List<String> buffer;

     MyConsumer(List<String> buffer, String color) {
        this.color = color;
        this.buffer = buffer;
    }

    @Override
    public void run() {
            while (true) {
                synchronized (buffer) {
                if (buffer.isEmpty()) {
                    continue;
                }
                if (buffer.get(0).equals(EOF)) {
                    System.out.println(color + "reached end of the file");
                    break;
                } else {
                    System.out.println(color + "removed " + buffer.remove(0));
                }
            }
        }

    }
}
