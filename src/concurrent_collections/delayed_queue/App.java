package concurrent_collections.delayed_queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

class DelayedWorker implements Delayed{

    private long duraion;
    private String message;

    public DelayedWorker(long duraion, String message) {
        this.duraion = System.currentTimeMillis()+duraion;
        this.message = message;
    }

    public long getDuraion() {
        return duraion;
    }

    public void setDuraion(long duraion) {
        this.duraion = duraion;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(duraion-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
      if( this.duraion<((DelayedWorker) o).getDuraion() )
          return -1;
      else if(this.duraion>((DelayedWorker) o).getDuraion())
          return 1;
      return 0;
    }

    @Override
    public String toString() {
        return "DelayedWorker{" +
                "duraion=" + duraion +
                ", message='" + message + '\'' +
                '}';
    }
}
public class App {
    public static void main(String[] args) {

        BlockingQueue<DelayedWorker> queue=new DelayQueue<>();

        try {
            queue.put(new DelayedWorker(1000,"This is the first message"));
            queue.put(new DelayedWorker(2000,"This is the 2 message"));
            queue.put(new DelayedWorker(5000,"This is the 3 message"));
            queue.put(new DelayedWorker(10000,"This is the 4 message"));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!queue.isEmpty()){
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
