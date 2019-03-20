package concurrent_collections.latches;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Worker implements Runnable {

    private int id;
    private CountDownLatch countDownLatch;
    private Random random;

    public Worker(int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        doWork();
        countDownLatch.countDown();
    }

    private void doWork() {
        System.out.println("Thread with id : " + this.id + " Works");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class App {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch latch=new CountDownLatch(5);
        List<Worker> workers;

        IntStream.range(0,5).forEach((i)->
        executorService.execute(new Worker(i,latch)));

        workers = IntStream.range(0, 5).mapToObj(i -> new Worker(i, latch)).collect(Collectors.toList());
               workers.stream().forEach(CompletableFuture::runAsync);

        IntStream.range(0, 5)
                .mapToObj(i -> new Worker(i, latch))
                .collect(Collectors.toList())
                .stream()
                .forEach(CompletableFuture::runAsync);

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All done ");
        executorService.shutdown();
    }
}
