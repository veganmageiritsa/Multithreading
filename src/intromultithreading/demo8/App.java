package intromultithreading.demo8;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {
    private CountDownLatch latch;

    public Processor(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("Starting : ");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
//        System.out.println("Completed : " );

    }
}
public class App {


    public static void main(String[] args) {

        //thread safe , lets one or more threads wait until number goes to 0
        CountDownLatch latch= new CountDownLatch(3);

        ExecutorService executorService = Executors.newFixedThreadPool(6);
        for (int i = 1; i <= 3; i++)
            executorService.submit(new Processor(latch));

        try {
            latch.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
        System.out.println(" Completed " + latch.getCount() );
    }
}
