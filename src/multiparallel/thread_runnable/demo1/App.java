package multiparallel.thread_runnable.demo1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static multiparallel.thread_runnable.demo2.App.startThreads;

public class App {

    // problem while accessing the same counter so use atomicInteger
//    private static int counter=0;
//    private static AtomicInteger counter=new AtomicInteger(0);
    //or use synchronized
    private static int counter=0;

    public static void process(){

//        Thread t1 = new Thread(() -> IntStream.range(0, 10000)
//                .forEach((i) -> ++counter));
//
//        Thread t2 = new Thread(() -> IntStream.range(0, 10000)
//                .forEach((i) -> ++counter));

        // AtomicInteger Solution
//        Thread t1 = new Thread(() -> IntStream.range(0, 10000)
//                .forEach((i) -> counter.addAndGet(1)));
//
//        Thread t2 = new Thread(() -> IntStream.range(0, 10000)
//                .forEach((i) -> counter.addAndGet(1)));

        // Synchronized Solution
        Runnable runnable = () -> IntStream.range(0, 10000)
                .forEach((i) -> increment());
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);

       startThreads(t1,t2);


    }

    private static synchronized void increment() {
        ++counter;
    }

    public static void main(String[] args) {

        process();
        System.out.println(counter);
    }
}
