package br.com.llocatti.projetoweb2.services;

import br.com.llocatti.projetoweb2.entities.Book;
import br.com.llocatti.projetoweb2.entities.Category;
import br.com.llocatti.projetoweb2.entities.Publisher;
import br.com.llocatti.projetoweb2.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {
    @Autowired
    private BooksRepository booksRepository;

    public Book create(Book book) {
        book = booksRepository.save(book);

        return book;
    }

    public List<Book> getAll() {
        return booksRepository.findAll();
    }

    public Book getBook(long id) {
        return booksRepository.findById(id).orElse(null);
    }

    public Book update(Book book) {
        book = booksRepository.save(book);

        return book;
    }

    public void delete(Long id) {
        booksRepository.deleteById(id);
    }

    public List<Book> findByCategory(Category category) {
        return booksRepository.findByCategory(category);
    }

    public List<Book> findByPublisher(Publisher publisher) {
        return booksRepository.findByPublisher(publisher);
    }
}
