package intromultithreading.demo11;

public class App {

    public static void main(String[] args) throws InterruptedException {

        final Runner runner = new Runner();

        Thread t1 = new Thread(() -> runner.firstThread());


        Thread t2 = new Thread(() ->
                runner.secondThread());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        runner.finished();
    }
}
