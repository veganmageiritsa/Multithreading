package intromultithreading.demo9;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {

    private static BlockingQueue<Integer> queue=new ArrayBlockingQueue<Integer>(10);

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> producer());
        Thread t2 = new Thread(() -> producer());
        Thread t3 = new Thread(() -> consumer());
        Thread t4 = new Thread(() -> consumer());
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    private static void producer(){

        while (true) {
            try {
                Random random=new Random();
                queue.put(random.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void consumer(){
        while (true){
            try {
                Thread.sleep(100);
//                if (random.nextInt(10)==0) {
                    Integer number = queue.take();
                    System.out.println("Value consumed is : " +number +" Queue size is : " + queue.size());
//                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
