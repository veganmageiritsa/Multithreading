package intromultithreading.demo3;

import java.util.Scanner;

class Processor extends Thread {

    private volatile boolean running = true;

    public void run() {
        while (running) {
            System.out.println("Hello");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void shutDown() {
        running = false;
    }
}

public class App {

    public static void main(String[] args) {
        Processor proc1 = new Processor();
        proc1.start();
        System.out.println("Press Enter to stop");
        Scanner sc= new Scanner(System.in);
        sc.nextLine();
        proc1.shutDown();


        // There is a chance that running will stay true because we are running in two different threads (main and the other)
        // main modifies data of Processor to avoid it use volatile (prevent threads caching variables in order to use variables from other threads
    }
}
