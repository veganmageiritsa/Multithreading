package multiparallel.thread_runnable.prod_cons_lock;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static multiparallel.thread_runnable.demo2.App.startThreads;

class Worker {
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

    private final Lock aLock = new ReentrantLock();
    private final Condition bufferNotFull = aLock.newCondition();
    private final Condition bufferNotEmpty = aLock.newCondition();
    private static int maxQueueSize = 10;
    private final Random theRandom = new Random();



    public void producer() {
        aLock.lock();
        try {
            while(true){
                while (queue.size() == maxQueueSize) {
                    System.out.println(Thread.currentThread().getName()
                            + " : Buffer is full, waiting");
                    bufferNotEmpty.await();
                }
                int number = theRandom.nextInt();
                boolean isAdded = queue.offer(number);
                if (isAdded) {
                    System.out.printf("%s added %d into queue %n", Thread
                            .currentThread().getName(), number);
                }
                bufferNotFull.signalAll();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            aLock.unlock();
        }
    }


    public void consumer() {
        aLock.lock();
        try {
            while(true){
                while (queue.size() == 0) {
                    System.out.println(Thread.currentThread().getName()
                            + " : Buffer is empty, waiting");
                    bufferNotFull.await();
                }
                Integer value = (Integer)queue.poll();
                if (value != null) {
                    System.out.printf("%s consumed %d from queue %n", Thread
                            .currentThread().getName(), value);
                }
                bufferNotEmpty.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            aLock.unlock();
        }

    }
}

public class App {


    public static void main(String[] args) {

        Worker worker = new Worker();
        Thread t1 = new Thread(() -> worker.producer());
        Thread t2 = new Thread(() -> worker.consumer());
        Thread t3 = new Thread(() -> worker.producer());
        Thread t4 = new Thread(() -> worker.consumer());

        startThreads(t1, t2);
        startThreads(t3, t4);

    }

}
