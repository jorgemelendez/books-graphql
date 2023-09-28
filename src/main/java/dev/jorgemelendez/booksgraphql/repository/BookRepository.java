package dev.jorgemelendez.booksgraphql.repository;

import dev.jorgemelendez.booksgraphql.model.Author;
import dev.jorgemelendez.booksgraphql.model.Book;
import dev.jorgemelendez.booksgraphql.model.Rating;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
  private final AuthorRepository authorRepository;
  private List<Book> books = new ArrayList<>();
  private AtomicInteger idTracker = new AtomicInteger();

  public BookRepository(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  public Book addBook(String title, Integer pages, Rating rating, Author author) {
    Book newBook = new Book(idTracker.getAndIncrement(), title, pages, rating, author);
    books.add(newBook);
    return newBook;
  }

  public List<Book> findAll() {
    return books;
  }

  public Book findOne(Integer id) {
    return books.stream().filter(book -> book.id().equals(id))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Book not found"));
  }

  @PostConstruct
  private void init() {
    books.add(new Book(idTracker.getAndIncrement(), "Reactive Spring", 484, Rating.FIVE_STARS, authorRepository.findByName("Josh Long")));
    books.add(new Book(idTracker.getAndIncrement(), "Spring Boot up & Running", 328, Rating.FIVE_STARS, authorRepository.findByName("Mark Heckler")));
    books.add(new Book(idTracker.getAndIncrement(), "Hacking with Spring Boot 2.3", 484, Rating.FIVE_STARS, authorRepository.findByName("Greg Turnquist")));

  }
}
