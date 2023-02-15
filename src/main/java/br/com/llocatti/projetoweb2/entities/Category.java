package br.com.llocatti.projetoweb2.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue
    private long id;
    private String name;
}
