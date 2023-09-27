package dev.jorgemelendez.booksgraphql.controller;

import dev.jorgemelendez.booksgraphql.model.Book;
import dev.jorgemelendez.booksgraphql.repository.BookRepository;
import java.util.List;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {

  private final BookRepository bookRepository;

  public BookController(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  // @SchemaMapping(typeName = "Query", value = "allBooks")
  @QueryMapping
  public List<Book> allBooks() {
    return bookRepository.findAll();
  }
}
