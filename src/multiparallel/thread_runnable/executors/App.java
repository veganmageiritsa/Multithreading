package multiparallel.thread_runnable.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class App {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        IntStream.range(0, 5).forEach((e) -> executorService.execute(new Worker()));
        try {
            executorService.awaitTermination(1000, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}


class Worker implements Runnable {

    @Override
    public void run() {
        IntStream.range(0, 10).forEach((i) -> System.out.println(i));
    }
}