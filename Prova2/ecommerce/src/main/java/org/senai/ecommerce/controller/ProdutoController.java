package org.senai.ecommerce.controller;
import org.senai.ecommerce.entity.Produto;
import org.senai.ecommerce.entity.ProdutoDTO;
import org.senai.ecommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String criarProduto(@RequestBody ProdutoDTO produto){
        produtoService.salvar(produto);
        return "produto adicionado com sucesso";
    }

    @GetMapping("/lista")
    public ResponseEntity<List<ProdutoDTO>> listarProdutos(){
        return new ResponseEntity<>(produtoService.getProdutos(), HttpStatus.OK);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Produto> getProduto(@PathVariable("codigo") Long codigo){
        return new ResponseEntity<>(produtoService.getRepo().findById(codigo).get(), HttpStatus.OK);
    }

    @PutMapping("/alterar/{codigo}")
    public ResponseEntity<Produto> alterar(@RequestBody Produto produto,
                                         @PathVariable("codigo") Long codigo){
        Optional<Produto> produtoOptional = produtoService.getRepo().findById(codigo);
        if (produtoOptional.isPresent()){
            Produto produtoPersistir = produtoOptional.get();
            produtoPersistir.setNome(produto.getNome());
            produtoPersistir.setDescricao(produto.getDescricao());
            produtoPersistir.setPreco(produto.getPreco());
            produtoPersistir.setEstoque(produto.getEstoque());
            produtoService.getRepo().save(produtoPersistir);
        }
        return new ResponseEntity(produto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{codigo}")
    public ResponseEntity excluir(@PathVariable("codigo") Long codigo){
        produtoService.getRepo().deleteById(codigo);
        return new ResponseEntity("Aluno excluído com sucesso", HttpStatus.OK);
    }

    /*@GetMapping
    public ResponseEntity<?> getProdutos() {
        return new ResponseEntity<>(produtoService.getProdutos(), HttpStatus.OK);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> getProdutoPorCodigo(@PathVariable("codigo") Long codigo) {
        return new ResponseEntity<>(produtoService.getProdutoPorCodigo(codigo), HttpStatus.OK);


    @PostMapping
    public ResponseEntity<?> criarProduto(@RequestBody Produto produto) {
        return new ResponseEntity<>(produtoService.criarProduto(produto), HttpStatus.CREATED);


    @PutMapping("/{codigo}")
    public ResponseEntity<?> atualizarProduto(@PathVariable("codigo") String codigo, @RequestBody Produto productDetails) {
        return new ResponseEntity<>(produtoService.atualizarProduto(codigo, productDetails), HttpStatus.OK);


    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> deleteProduct(@PathVariable String codigo) {
        produtoService.excluirProduto(codigo);
        return new ResponseEntity<>("Produto Excluído do Sucesso!", HttpStatus.OK);*/
    }
}
