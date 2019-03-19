package multiparallel.thread_runnable.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

class Processor implements Callable<String> {

    private int id;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return "Id: " + id;
    }
}

public class App {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        List<Future<String>> futures = new ArrayList<>();
        IntStream.range(0, 5).forEach((i) -> {
            Future<String> future = executorService.submit(new Processor(i));
            futures.add(future);
        });

        futures.forEach(f -> {
            try {
                System.out.println(f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
    }
}
