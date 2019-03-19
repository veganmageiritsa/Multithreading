package multiparallel.thread_runnable.reentrant_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

import static multiparallel.thread_runnable.demo2.App.startThreads;

public class App {

    private static Lock lock = new ReentrantLock();
    private static int counter;


    public static void increment(){
        try {
            lock.lock();
            IntStream.range(0, 100000).forEach((i) -> counter++);
        }finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {

        Thread t1=new Thread(()->increment());
        Thread t2=new Thread(()->increment());

        startThreads(t1,t2);
        System.out.println("Counter : " + counter);
    }
}
