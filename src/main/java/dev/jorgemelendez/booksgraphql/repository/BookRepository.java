package dev.jorgemelendez.booksgraphql.repository;

import dev.jorgemelendez.booksgraphql.model.Book;
import dev.jorgemelendez.booksgraphql.model.Rating;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
  private final AuthorRepository authorRepository;
  private List<Book> books = new ArrayList<>();

  public BookRepository(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
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
    books.add(new Book(1, "Reactive Spring", 484, Rating.FIVE_STARS, authorRepository.findByName("Josh Long")));
    books.add(new Book(2, "Spring Boot up & Running", 328, Rating.FIVE_STARS, authorRepository.findByName("Mark Heckler")));
    books.add(new Book(3, "Hacking with Spring Boot 2.3", 484, Rating.FIVE_STARS, authorRepository.findByName("Greg Turnquist")));

  }
}
