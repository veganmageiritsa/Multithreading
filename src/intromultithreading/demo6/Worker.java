package intromultithreading.demo6;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {


    // good practice to lock in seperate objects  and not the lists themselves
    private Object lock1=new Object();
    private Object lock2=new Object();
    private Random random = new Random();
    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    public void main() {
        System.out.println("Starting .....");
        long start = System.currentTimeMillis();
        // one thread
//        process();

        // multiple threads
        Thread t1 = new Thread(() -> process());
        Thread t2 = new Thread(() -> process());
        Thread t3 = new Thread(() -> process());
        Thread t4 = new Thread(() -> process());
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("Time Duration : " + (end - start));
        System.out.println("List 1 : " + list1.size() + " List 2 : " + list2.size());
    }

    // synchronized is a problem because this class has one intrinsic lock so all the other threads have to wait
    // if one has acquired it , but group of threads write to different data structures so we must have something to not allow both threads to run stageOne or stageTwo
    // we need two locks and lock code instead of method

    public  void stageOne() {
        synchronized (lock1){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(random.nextInt(100));
        }

    }

    public synchronized void stageTwo() {
        synchronized (lock2){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list2.add(random.nextInt(100));
        }

    }

    public void process() {
        for (int i = 0; i < 1000; i++) {
            stageOne();
            stageTwo();
        }
    }
}
