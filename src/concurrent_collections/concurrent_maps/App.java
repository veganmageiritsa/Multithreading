package concurrent_collections.concurrent_maps;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.IntStream;

class Producer implements Runnable {

    private ConcurrentMap<String, Integer> map;

    public Producer(ConcurrentMap<String, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {

        IntStream.range(0, 50)
                .forEach((r) -> map.putIfAbsent(String.valueOf((char) (new Random().nextInt(26) + 'a')), new Random().nextInt(100)));

    }
}
    class Consumer implements Runnable{
        private ConcurrentMap<String, Integer> map;

        public Consumer(ConcurrentMap<String, Integer> map) {
            this.map = map;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
                map.forEach((k,v)-> System.out.println(k+" "+ v));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

public class App {


    public static void main(String[] args) {
         ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();
        new Thread( new Producer(map)).start();
        new Thread( new Consumer(map)).start();

    }

}
