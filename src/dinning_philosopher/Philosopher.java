package dinning_philosopher;

public class Philosopher implements Runnable{
    private final int seat;
    private final StickHolder left, right;
    private int eatingCounter;
    private boolean isFull;
    public Philosopher(int seat,
            StickHolder left, StickHolder right) {
        this.seat = seat;
        this.left = left;
        this.right = right;
    }
    @Override
    public String toString() {
        return "P" + seat;
    }
    @Override
    public void run() {
        while(!isFull) {
            // System.out.println("Thinking");   // [1]
            right.pickUp();
            left.pickUp();
            eatingCounter++;
//            System.out.println(this + " eating");
            right.putDown();
            left.putDown();
        }
    }

    public void setEatingCounter(int eatingCounter) {
        this.eatingCounter = eatingCounter;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public int getSeat() {
        return seat;
    }

    public int getEatingCounter() {
        return eatingCounter;
    }
}
