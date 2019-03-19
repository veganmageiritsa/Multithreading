package intromultithreading.demo7;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {

    public static class Processor implements Runnable {
        private int id;

        public Processor(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println("Starting : " + id);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Completed : " + id);

        }
    }

    public static void main(String[] args) {

        // assign tasks to threads. When they finish they start the new task
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        long start = System.currentTimeMillis();
        for (int i = 1; i <= 5; i++)
            executorService.submit(new Processor(i));
        executorService.shutdown();

        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
            System.out.println("All tasks completed");
            long end = System.currentTimeMillis();
            System.out.println("Time Duration : " + (end - start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
