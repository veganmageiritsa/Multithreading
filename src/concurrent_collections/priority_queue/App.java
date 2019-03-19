package concurrent_collections.priority_queue;


import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
class Person{
    int age;
    String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
class Producer implements Runnable {
    private BlockingQueue<Person> queue;

    public Producer(BlockingQueue<Person> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            queue.put(new Person(13,"Adam"));
            queue.put(new Person(61,"Sarah"));
            queue.put(new Person(23,"George"));
            queue.put(new Person(31,"Viel"));
            queue.put(new Person(38,"Jup"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {
    private BlockingQueue<Person> queue;

    public Consumer(BlockingQueue<Person> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while(!queue.isEmpty()) {
                Thread.sleep(1000);
                System.out.println(queue.take());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class App {
    public static void main(String[] args) {
        Comparator<Person> ageComparator = Comparator.comparing(Person::getAge).reversed();
        BlockingQueue<Person> queue = new PriorityBlockingQueue<>( 11, ageComparator );

        new Thread( new Producer(queue)).start();
        new Thread( new Consumer(queue)).start();

    }
}
