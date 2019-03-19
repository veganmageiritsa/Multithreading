package intromultithreading.demo10;

public class App {

    public static void main(String[] args) {
        final Processor processor = new Processor();

        Thread t1 = new Thread(() -> processor.produce());


        Thread t2 = new Thread(() -> processor.consume());


        t1.start();
        t2.start();
//        t1.join();
//        t2.join();

        // Pause for 30 seconds and force quitting the app (because we're
        // looping infinitely)
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
