package com.example.demo.carrinho.repository;

import com.example.demo.carrinho.entity.Carrinho;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CarrinhoRepository extends CrudRepository<Carrinho, UUID> {
}
