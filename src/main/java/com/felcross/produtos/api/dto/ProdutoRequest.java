package com.felcross.produtos.api.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoRequest {
    @NotBlank private String nome;
    private String descricao;
    @NotNull @DecimalMin("0.01") private BigDecimal preco;
    @NotNull @Min(0) private Integer estoque;
}
