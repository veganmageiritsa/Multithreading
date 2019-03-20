package concurrent_collections.exchanger;

import java.util.concurrent.Exchanger;

class Worker implements Runnable{
    private Exchanger<Integer> exchanger;
    private int counter;
    public Worker(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while(true){
            counter++;
            System.out.println("Worker 1 incremented counter " + counter);
            try {
                counter=exchanger.exchange(counter);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class App {

    public static void main(String[] args) {

    }
}
