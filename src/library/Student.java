package library;

import java.util.List;
import java.util.Random;

public class Student implements Runnable{
    private int id;
    private List<Book> books;

    public Student(int id, List<Book> books) {
        this.books = books;
        this.id = id;
    }

    @Override
    public void run() {

        Random random = new Random();

        while (true) {

            int bookId = random.nextInt(Constants.BOOKS);

            try {
                books.get(bookId).read(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String toString() {
        return "Student" + id;
    }
}
