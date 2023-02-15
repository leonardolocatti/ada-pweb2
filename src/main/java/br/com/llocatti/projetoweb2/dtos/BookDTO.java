package br.com.llocatti.projetoweb2.dtos;

import lombok.Data;

@Data
public class BookDTO {
    private long id;
    private long publisherId;
    private long categoryId;
    private String name;
    private String isbn;
}
