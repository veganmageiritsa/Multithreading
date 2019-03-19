package intromultithreading.demo13;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class App {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService service= Executors.newCachedThreadPool();

        IntStream.range(1,200).forEach((e)->service.submit(()->Connection.getConnection().connect()));

        service.shutdown();
        service.awaitTermination(100, TimeUnit.MINUTES);
    }
}
