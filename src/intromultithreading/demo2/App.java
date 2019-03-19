package intromultithreading.demo2;

class Runner implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Hello" + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class App {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runner());
        t1.start();
        Thread t2 = new Thread(new Runner());
        t2.start();

       Thread t3=new Thread(() -> System.out.println("Task #3 is running"));

        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Hello" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}
