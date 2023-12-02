package org.senai.ecommerce.service;

import jakarta.persistence.EntityNotFoundException;
import org.senai.ecommerce.entity.Produto;
import org.senai.ecommerce.entity.ProdutoDTO;
import org.senai.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService{

    @Autowired
    ProdutoRepository repository;

    List<Produto> produtos;

    public List<Produto> getProdutos() {
        return repository.findAll();
    }

    public Optional<Produto> getProdutoPorCodigo(String codigo) {
        return repository.findById(codigo);
    }

/*    public Produto criarProduto(Produto newProdutoDTO) {
        Produto produto = new Produto();
        produto.setNome(newProdutoDTO.getNome());
        produto.setDescricao(newProdutoDTO.getDescricao());
        produto.setPreco(newProdutoDTO.getPreco());
        produto.setEstoque(newProdutoDTO.getEstoque());
        produto = repository.save(produto);
        return produto.getCodigo();
    }*/

/*    public ResponseEntity<String> atualizarProduto(String codigo, ProdutoDTO produtoDTO) {
        Produto existeProduto = repository.findById(codigo)
                .orElseThrow(()-> new EntityNotFoundException("Produto nao encontrado"));

        if (produtoDTO.getNome() != null){
            existeProduto.setNome(produtoDTO.getNome());
        }
        if (produtoDTO.getDescricao() != null){
            existeProduto.setDescricao(produtoDTO.getDescricao());
        }
        if (produtoDTO.getPreco() != null){
            existeProduto.setPreco(produtoDTO.getPreco());
        }
        if (produtoDTO.getEstoque() != null){
            existeProduto.setEstoque(produtoDTO.getEstoque());
        }

        existeProduto.setCompleted(produtoDTO.isCompleted());
        repository.save(existeProduto);

        return ResponseEntity.ok("Produto atualizado com sucesso!");
    }*/

    public void excluirProduto(String codigo) {
        repository.deleteById(codigo);
    }
    public ProdutoRepository getRepo(){
        return ProdutoRepository;
    }
}
