package org.senai.ecommerce.config;

import org.senai.ecommerce.entity.Produto;
import org.senai.ecommerce.service.ProdutoService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UserDetailsServiceCustom implements UserDetailsService {
    private final ProdutoService produtoService;

    public UserDetailsServiceCustom(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Produto> produto = produtoService.getProdutoAutenticacao(username);
        if(!produto.isPresent())
            new UsernameNotFoundException("Produto não encontrado!");

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(produto.get().getPermissao());
        Set<GrantedAuthority> authorities = new HashSet();
        authorities.add(authority);

        User user = new User(produto.get().getCodigo(), produto.get().getSenha(), authorities);

        return user;
    }

}
