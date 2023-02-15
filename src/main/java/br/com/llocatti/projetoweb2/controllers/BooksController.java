package br.com.llocatti.projetoweb2.controllers;

import br.com.llocatti.projetoweb2.dtos.BookDTO;
import br.com.llocatti.projetoweb2.entities.Book;
import br.com.llocatti.projetoweb2.entities.Category;
import br.com.llocatti.projetoweb2.entities.Publisher;
import br.com.llocatti.projetoweb2.services.BooksService;
import br.com.llocatti.projetoweb2.services.CategoriesService;
import br.com.llocatti.projetoweb2.services.PublishersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BooksService booksService;
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private PublishersService publishersService;

    private final ModelMapper mapper = new ModelMapper();

    @PostMapping
    public ResponseEntity<BookDTO> create(@RequestBody BookDTO bookDTO) {
        Category category = categoriesService.getCategory(bookDTO.getCategoryId());
        Publisher publisher = publishersService.getPublisher(bookDTO.getPublisherId());

        Book book = mapper.map(bookDTO, Book.class);
        book.setCategory(category);
        book.setPublisher(publisher);

        book = booksService.create(book);

        bookDTO = mapper.map(book, BookDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(bookDTO);
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAll() {
        List<Book> books = booksService.getAll();

        List<BookDTO> booksDTO = books.stream().map(book -> mapper.map(book, BookDTO.class)).toList();

        return ResponseEntity.ok(booksDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getById(@PathVariable long id) {
        Book book = booksService.getBook(id);

        BookDTO bookDTO = mapper.map(book, BookDTO.class);

        return ResponseEntity.ok(bookDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable long id, @RequestBody BookDTO bookDTO) {
        Category category = categoriesService.getCategory(bookDTO.getCategoryId());
        Publisher publisher = publishersService.getPublisher(bookDTO.getPublisherId());

        Book book = mapper.map(bookDTO, Book.class);
        book.setCategory(category);
        book.setPublisher(publisher);

        book = booksService.update(book);

        bookDTO = mapper.map(book, BookDTO.class);

        return ResponseEntity.ok(bookDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        booksService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
