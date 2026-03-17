package com.felcross.produtos.api.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data @Builder
public class ProdutoResponse {
    private String id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer estoque;
    private LocalDateTime createdAt;
}
