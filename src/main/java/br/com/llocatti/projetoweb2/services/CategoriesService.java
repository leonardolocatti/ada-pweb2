package br.com.llocatti.projetoweb2.services;

import br.com.llocatti.projetoweb2.entities.Category;
import br.com.llocatti.projetoweb2.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;

    public Category create(Category category) {
        category = categoriesRepository.save(category);

        return category;
    }

    public List<Category> getAll() {
        return categoriesRepository.findAll();
    }

    public Category getCategory(long id) {
        return categoriesRepository.findById(id).orElse(null);
    }

    public Category update(Category category) {
        category = categoriesRepository.save(category);

        return category;
    }

    public void delete(Long id) {
        categoriesRepository.deleteById(id);
    }
}
