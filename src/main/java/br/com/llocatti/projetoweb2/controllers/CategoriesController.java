package br.com.llocatti.projetoweb2.controllers;

import br.com.llocatti.projetoweb2.dtos.BookDTO;
import br.com.llocatti.projetoweb2.dtos.CategoryDTO;
import br.com.llocatti.projetoweb2.entities.Book;
import br.com.llocatti.projetoweb2.entities.Category;
import br.com.llocatti.projetoweb2.services.BooksService;
import br.com.llocatti.projetoweb2.services.CategoriesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private BooksService booksService;

    private ModelMapper mapper = new ModelMapper();

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO categoryDTO) {
        Category category = mapper.map(categoryDTO, Category.class);

        category = categoriesService.create(category);

        categoryDTO = mapper.map(category, CategoryDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDTO);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<Category> categories = categoriesService.getAll();

        List<CategoryDTO> categoriesDTO = categories.stream().map(category -> mapper.map(category, CategoryDTO.class)).toList();

        return ResponseEntity.ok(categoriesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable long id) {
        Category category = categoriesService.getCategory(id);

        CategoryDTO categoryDTO = mapper.map(category, CategoryDTO.class);

        return ResponseEntity.ok(categoryDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable long id, @RequestBody CategoryDTO categoryDTO) {
        Category category = mapper.map(categoryDTO, Category.class);

        category = categoriesService.update(category);

        categoryDTO = mapper.map(category, CategoryDTO.class);

        return ResponseEntity.ok(categoryDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        categoriesService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookDTO>> books(@PathVariable long id) {
        Category category = categoriesService.getCategory(id);

        List<Book> books = booksService.findByCategory(category);

        List<BookDTO> booksDTO = books.stream().map(book -> mapper.map(book, BookDTO.class)).toList();

        return ResponseEntity.ok(booksDTO);
    }
}
