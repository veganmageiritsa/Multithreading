package concurrent_collections.cyclic_barriers;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

class Worker implements Runnable {

    private int id;
    private Random random;
    private CyclicBarrier cyclicBarrier;

    public Worker(int id, CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        doWork();
    }

    private void doWork() {
        System.out.println("Thread with id : " + this.id + " Starts");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread with id : " + this.id + " Finished");
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
public class App {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CyclicBarrier barrier = new CyclicBarrier(5,()-> System.out.println("All tasks finished"));
        IntStream.range(0,5).forEach((i)->
                executorService.execute(new Worker(i,barrier)));

        executorService.shutdown();
    }
}
