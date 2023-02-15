package br.com.llocatti.projetoweb2.services;

import br.com.llocatti.projetoweb2.entities.Publisher;
import br.com.llocatti.projetoweb2.repositories.PublishersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublishersService {
    @Autowired
    private PublishersRepository publishersRepository;

    public Publisher create(Publisher publisher) {
        publisher = publishersRepository.save(publisher);

        return publisher;
    }

    public List<Publisher> getAll() {
        return publishersRepository.findAll();
    }

    public Publisher getPublisher(long id) {
        return publishersRepository.findById(id).orElse(null);
    }

    public Publisher update(Publisher publisher) {
        publisher = publishersRepository.save(publisher);

        return publisher;
    }

    public void delete(Long id) {
        publishersRepository.deleteById(id);
    }
}
