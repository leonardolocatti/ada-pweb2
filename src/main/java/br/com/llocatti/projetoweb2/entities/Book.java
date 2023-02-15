package br.com.llocatti.projetoweb2.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Publisher publisher;
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
    private String name;
    private String isbn;
}
