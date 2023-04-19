package com.example.demo.carrinho.service;

import com.example.demo.carrinho.entity.Carrinho;
import com.example.demo.carrinho.repository.CarrinhoRepository;
import com.example.demo.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CarrinhoService {
    private final CarrinhoRepository repo;

    @Autowired
    public CarrinhoService(CarrinhoRepository repo) {
        this.repo = repo;
    }

    public Iterable<Carrinho> findAllProds() {
        return repo.findAll();
    }

    public Carrinho findProdById(UUID id) {
        return findOrThrow(id);
    }
    private Carrinho findOrThrow(final UUID id) {
        return repo
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Prod by id " + id + " was not found")
                        );
    }

    public void removeProdById(UUID id) {
        repo.deleteById(id);
    }

    public Carrinho addProd(
            Carrinho produto) {
        return repo.save(produto);
    }

    public void updateProd(UUID id,
                           Carrinho produto) {
        findOrThrow(id);
        repo.save(produto);
    }
}
