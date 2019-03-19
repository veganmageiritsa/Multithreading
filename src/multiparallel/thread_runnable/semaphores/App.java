package multiparallel.thread_runnable.semaphores;

import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.IntStream;

enum Downloader {

    INSTANCE;

    private Semaphore semaphore = new Semaphore(6, true);

    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(20000);

    public void downloadData() {
        try {
            semaphore.acquire();
            download();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    private void download() {

        IntStream.range(0, 10).forEach((e) ->{
            Integer a = new Random().nextInt(56);
                queue.offer(a);
                System.out.println(queue.peek());});
        System.out.println("Download Data");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class App {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        IntStream.range(0,12).forEach((e)->executorService.execute(()->Downloader.INSTANCE.downloadData()));

        executorService.shutdown();
        try {
            executorService.awaitTermination(5000,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
