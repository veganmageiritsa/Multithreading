package multiparallel.thread_runnable.producer_consumer;

import java.util.ArrayList;
import java.util.List;

import static multiparallel.thread_runnable.demo2.App.startThreads;

class Processor {


    private List<Integer> numbers = new ArrayList<>();
    private final int LIMIT = 5;
    private final int BOTTOM = 0;
    private final Object lock = new Object();
    private int value = 0;

    public void produce() {
        synchronized (lock) {
            while (true) {
                if (numbers.size() == LIMIT) {
                    System.out.println("Waiting Removing Items from list....");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Adding value : " + value);
                    numbers.add(value++);
                    lock.notify();
                }

            }
        }

    }

    public void consume() {
        try {
            // lock will be taken from producer because of sleep
            Thread.sleep(1000);

            synchronized (lock) {
                while (true) {
                    if (numbers.size() == BOTTOM) {
                        System.out.println("Waiting for adding items numbers....");
                        lock.wait();
                    } else {
                        System.out.println("Removed : " + numbers.remove(--value));
                        lock.notify();
                    }
                    Thread.sleep(500);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

public class App {
    public static void main(String[] args) {
        Processor processor = new Processor();
        Thread t1 = new Thread(() -> processor.produce());
        Thread t2 = new Thread(() -> processor.consume());

        startThreads(t1, t2);
    }
}
