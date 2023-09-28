package dev.jorgemelendez.booksgraphql.controller;

import dev.jorgemelendez.booksgraphql.model.Book;
import dev.jorgemelendez.booksgraphql.model.Rating;
import dev.jorgemelendez.booksgraphql.repository.BookRepository;
import java.util.List;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
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

  @QueryMapping
  public Book findById(@Argument Integer id) {
    return bookRepository.findOne(id);
  }

  @MutationMapping
  public Book createBook(
      @Argument String title,
      @Argument Integer pages,
      @Argument Integer rating,
      @Argument String authorName) {
    return bookRepository.addBook(title, pages, Rating.rating(rating), null);
  }
}
