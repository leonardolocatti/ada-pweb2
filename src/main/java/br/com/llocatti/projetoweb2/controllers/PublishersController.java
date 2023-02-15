package br.com.llocatti.projetoweb2.controllers;

import br.com.llocatti.projetoweb2.dtos.BookDTO;
import br.com.llocatti.projetoweb2.dtos.PublisherDTO;
import br.com.llocatti.projetoweb2.entities.Book;
import br.com.llocatti.projetoweb2.entities.Category;
import br.com.llocatti.projetoweb2.entities.Publisher;
import br.com.llocatti.projetoweb2.services.BooksService;
import br.com.llocatti.projetoweb2.services.PublishersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublishersController {
    @Autowired
    private PublishersService publishersService;
    @Autowired
    private BooksService booksService;

    private ModelMapper mapper = new ModelMapper();

    @PostMapping
    public ResponseEntity<PublisherDTO> create(@RequestBody PublisherDTO publisherDTO) {
        Publisher publisher = mapper.map(publisherDTO, Publisher.class);

        publisher = publishersService.create(publisher);

        publisherDTO = mapper.map(publisher, PublisherDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(publisherDTO);
    }

    @GetMapping
    public ResponseEntity<List<PublisherDTO>> getAll() {
        List<Publisher> publishers = publishersService.getAll();

        List<PublisherDTO> publishersDTO = publishers.stream().map(publisher -> mapper.map(publisher, PublisherDTO.class)).toList();

        return ResponseEntity.ok(publishersDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO> getById(@PathVariable long id) {
        Publisher publisher = publishersService.getPublisher(id);

        PublisherDTO publisherDTO = mapper.map(publisher, PublisherDTO.class);

        return ResponseEntity.ok(publisherDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherDTO> update(@PathVariable long id, @RequestBody PublisherDTO publisherDTO) {
        Publisher publisher = mapper.map(publisherDTO, Publisher.class);

        publisher = publishersService.update(publisher);

        publisherDTO = mapper.map(publisher, PublisherDTO.class);

        return ResponseEntity.ok(publisherDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        publishersService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookDTO>> books(@PathVariable long id) {
        Publisher publisher = publishersService.getPublisher(id);

        List<Book> books = booksService.findByPublisher(publisher);

        List<BookDTO> booksDTO = books.stream().map(book -> mapper.map(book, BookDTO.class)).toList();

        return ResponseEntity.ok(booksDTO);
    }
}
