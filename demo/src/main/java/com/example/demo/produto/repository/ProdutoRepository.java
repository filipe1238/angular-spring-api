package com.example.demo.produto.repository;

import com.example.demo.carrinho.entity.Carrinho;
import com.example.demo.produto.entity.Produto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ProdutoRepository extends CrudRepository<Produto, UUID> {
    List<Produto> findAllByCarrinhoAtualIn(Iterable<Carrinho> role);
}
