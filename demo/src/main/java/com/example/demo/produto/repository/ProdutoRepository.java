package com.example.demo.produto.repository;

import com.example.demo.produto.entity.Produto;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProdutoRepository extends CrudRepository<Produto, UUID> {
}
