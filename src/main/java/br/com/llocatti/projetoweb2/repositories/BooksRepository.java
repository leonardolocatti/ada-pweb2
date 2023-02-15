package br.com.llocatti.projetoweb2.repositories;

import br.com.llocatti.projetoweb2.entities.Book;
import br.com.llocatti.projetoweb2.entities.Category;
import br.com.llocatti.projetoweb2.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BooksRepository extends JpaRepository<Book, Long> {
    List<Book> findByCategory(Category category);
    List<Book> findByPublisher(Publisher publisher);
}
