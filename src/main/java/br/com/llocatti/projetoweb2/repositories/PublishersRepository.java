package br.com.llocatti.projetoweb2.repositories;

import br.com.llocatti.projetoweb2.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublishersRepository extends JpaRepository<Publisher, Long> {
}
