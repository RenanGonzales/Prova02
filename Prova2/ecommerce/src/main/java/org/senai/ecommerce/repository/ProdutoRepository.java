package org.senai.ecommerce.repository;

import org.senai.ecommerce.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, String> {
    List<Produto> findByNome(String nome);

    List<Produto> findByCodigo(String codigo);
}
