package dinning_philosopher;

import dinning_marx.Constants;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

public class DinningPhilosophers {
    private StickHolder[] sticks;
    private Philosopher[] philosophers;
    public DinningPhilosophers(int n) {
        sticks = new StickHolder[n];
        Arrays.setAll(sticks, i -> new StickHolder());
        philosophers = new Philosopher[n];
        Arrays.setAll(philosophers, i ->
                new Philosopher(i,
                        sticks[i], sticks[(i + 1) % n]));    // [1]
        // Fix by reversing stick order:
        // philosophers[1] =                     // [2]
        //   new Philosopher(0, sticks[0], sticks[1]);
        Arrays.stream(philosophers)
                .forEach(CompletableFuture::runAsync); // [3]
    }

    public StickHolder[] getSticks() {
        return sticks;
    }

    public void setSticks(StickHolder[] sticks) {
        this.sticks = sticks;
    }

    public Philosopher[] getPhilosophers() {
        return philosophers;
    }

    public void setPhilosophers(Philosopher[] philosophers) {
        this.philosophers = philosophers;
    }

    public static void main(String[] args) throws InterruptedException {
        // Returns right away:
        DinningPhilosophers dinningPhilosophers = new DinningPhilosophers(5);// [4]
// Keeps main() from exiting:
//        ScheduledExecutorService sched =
//                Executors.newScheduledThreadPool(1);

        Thread.sleep(Constants.SIMULATION_RUNNING_TIME);

        Arrays.stream(dinningPhilosophers.getPhilosophers())
                .forEach((p)->p.setFull(true));

//        sched.schedule( () -> {
//            System.out.println("Shutdown");
//            sched.shutdown();
//        }, 5, SECONDS);

        Arrays.stream(dinningPhilosophers.getPhilosophers())
                .forEach((p)-> System.out.println(p + " ate " + p.getEatingCounter()));

    }
}