package concurrent_collections.blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Producer implements Runnable {

    private BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int counter = 0;
        while (true) {
            try {
                queue.put(counter);
                System.out.println("Putting items to the queue : " + counter);
                counter++;
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    private BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int number = queue.take();
                System.out.println("Number out of queue : " + number);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class App {

    public static void main(String[] args) {

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(20);
//        Producer producer=new Producer(queue);
//        new Thread(producer).start();
//        Consumer consumer=new Consumer(queue);
//        new Thread(consumer).start();
 new Thread( new Producer(queue)).start();
 new Thread( new Consumer(queue)).start();

    }
}
