package intromultithreading.demo14;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {

    public static void main(String[] args) {
        ExecutorService service= Executors.newCachedThreadPool();
        Future<Integer> future = service.submit(() -> {
            System.out.println("Starting...");
            Random random = new Random();
            int duration = random.nextInt(4000);
            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread finished.");
            return duration;
        });


        service.shutdown();
//        service.awaitTermination()

        try {
            System.out.println(" Result is : " + future.get());
        } catch (InterruptedException  | ExecutionException e) {
            e.printStackTrace();
        }
//        IntStream.range(1,200).forEach((e)->service.submit(()->Connection.getConnection().connect()));
    }
}
