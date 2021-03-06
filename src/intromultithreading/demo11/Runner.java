package intromultithreading.demo11;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    private int count = 0;
private Lock lock =new ReentrantLock();
private Condition condition=lock.newCondition();


    private void increment() {
        for (int i = 0; i < 10000; i++)
            count++;
    }

    public void firstThread() {
        lock.lock();
        try {
            condition.await();
            increment();
        }catch (InterruptedException e){

        }finally {
            lock.unlock();
        }

    }

    public void secondThread() {
        lock.lock();
        try {
            increment();
        }finally {
            lock.unlock();
        }

    }

    public void finished() {
        System.out.println("Count is : " + count);
    }
}
