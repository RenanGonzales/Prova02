package org.senai.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "Produtos")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="codigo")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String codigo;

    private String nome;
    private String descricao;
    private double preco;
    private int estoque;

}
