package intromultithreading.demo10;

import java.util.LinkedList;
import java.util.Random;

public class Processor {

    private LinkedList<Integer> list = new LinkedList<>();
    private final int LIMIT=10;
    private Object lock=new Object();

    public void produce()  {
        Random random=new Random();
        while(true){
            synchronized (lock) {
                while(list.size()==LIMIT) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                list.add(random.nextInt(100));
                System.out.println("Added value : " + list.peekFirst());
                lock.notify();
            }
        }

    }

    public void consume() {

        while ( true){
            synchronized (lock) {
                while (list.size()==0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(" Value removed : " + list.removeFirst());
                System.out.println("Size is : " + list.size());
                lock.notify();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
