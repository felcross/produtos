package com.felcross.produtos.domain.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "produtos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Produto {
    @Id
    private String id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer estoque;

    @org.springframework.data.annotation.CreatedDate // Anotação correta para Mongo
    private LocalDateTime createdAt;

}