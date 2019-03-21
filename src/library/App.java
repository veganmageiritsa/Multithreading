package library;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {

    public static void main(String[] args) {
        List<Student> students;
        List<Book> books = new ArrayList<>(Constants.BOOKS);

        try{
            IntStream.range(0, Constants.BOOKS).forEach(i -> books.add( new Book(i)));

            students = IntStream.range(0, Constants.STUDENTS)
                    .mapToObj(i -> new Student(i, books))
                    .collect(Collectors.toCollection(() -> new ArrayList<>(Constants.STUDENTS)));

            students.stream()
                    .forEach(CompletableFuture::runAsync);
            Thread.sleep(library.Constants.SIMULATION_RUNNING_TIME);

        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
