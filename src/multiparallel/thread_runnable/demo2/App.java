package multiparallel.thread_runnable.demo2;

import java.util.stream.IntStream;

public class App {

    private static int count1 = 0;
    private static int count2 = 0;


    // but intrinsic lock do not allow multiple threads access the synchronized blocks at the same time
    public synchronized static void add1() {
        synchronized (lock1) {
            count1++;
        }
    }

    public  static void add2() {
        synchronized (lock2) {
            count2++;
        }
    }

    //solution is to use two objects for lock and synchronize on them
    private static Object lock1 = new Object();
    private static Object lock2= new Object();

    public static void compute() {
        IntStream.range(0, 10000).forEach(i -> {
            add1();
            add2();
        });
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> compute());
        Thread t2 = new Thread(() -> compute());


        startThreads(t1, t2);

        System.out.println("Count1 = " + count1 );
        System.out.println("Count2 = " + count2 );

    }

    public static void startThreads(Thread t1, Thread t2) {
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
