package com.felcross.produtos.api.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProdutoRequest {
    @NotBlank private String nome;
    private String descricao;
    @NotNull @DecimalMin("0.01") private BigDecimal preco;
    @NotNull @Min(0) private Integer estoque;
}
