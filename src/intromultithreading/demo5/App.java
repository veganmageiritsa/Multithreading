package intromultithreading.demo5;

import java.util.concurrent.atomic.AtomicInteger;

public class App {

    private AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {

        App app = new App();
        app.doWork();
    }

    private void doWork() {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
        });

        t1.start();
        t2.start();
        // we get 0 because no thread has started, so use join

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //continue from demo 4 use AtomicInteger and not synchronized
        System.out.println("Count is : " + count);
    }

    private void increment() {
        count.addAndGet(1);
    }
}
