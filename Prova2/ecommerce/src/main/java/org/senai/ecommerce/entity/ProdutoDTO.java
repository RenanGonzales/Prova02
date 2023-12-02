package org.senai.ecommerce.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProdutoDTO {

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @NotBlank
    private double preco;
    @NotBlank
    private int estoque;
}
