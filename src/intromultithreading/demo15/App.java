package intromultithreading.demo15;

import java.util.Random;
import java.util.stream.IntStream;

public class App {

    public static void main(String[] args) throws InterruptedException {


        System.out.println("Starting ....");
        Random random = new Random();
        Thread t1 = new Thread(() -> IntStream.range(1, 1000000000)
                .forEach((e) -> {
                   if(Thread.currentThread().isInterrupted())
                    System.out.println("Interrupted");
                    Math.sin(random.nextDouble());
                }));

        t1.start();
        t1.interrupt();
        t1.join();
        System.out.println("Finished");

    }
}
