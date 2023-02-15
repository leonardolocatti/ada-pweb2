package br.com.llocatti.projetoweb2.repositories;

import br.com.llocatti.projetoweb2.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Category, Long> {
}
