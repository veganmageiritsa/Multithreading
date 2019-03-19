package intromultithreading.demo4;

public class App {

    private int count=0;
    public static void main(String[] args) {
        
        App app=new App();
        app.doWork();
    }

    private void doWork() {

        Thread t1=new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
        });

        Thread t2=new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
        });

        t1.start();
        t2.start();
        // we get 0 because no thread has started, so use join

        try {
            t1.join();
            t2.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        };

        // we dont get 2000 because two threads have simultaneously access to variable count, so use AtomicInteger or synchronized
        // problem is interleaving no caching so volatile won't help
        // synchronized means that only one thread acquires intrinsic lock at one time
        System.out.println("Count is : " + count);
    }

    private synchronized void increment() {
        count++;
    }
}
