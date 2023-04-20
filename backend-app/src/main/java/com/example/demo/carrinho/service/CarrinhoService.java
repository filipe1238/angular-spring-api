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

    public Iterable<Carrinho> findAllCarrinhos() {
        return repo.findAll();
    }

    public Carrinho findCarrinhoById(UUID id) {
        return findOrThrow(id);
    }
    private Carrinho findOrThrow(final UUID id) {
        return repo
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Carrinho by id " + id + " was not found")
                        );
    }

    public void removeCarrinhoById(UUID id) {
        repo.deleteById(id);
    }

    public Carrinho addCarrinho(
            Carrinho carrinho) {
        return repo.save(carrinho);
    }

    public void updateCarrinho(UUID id,
                               Carrinho carrinho) {
        findOrThrow(id);
        repo.save(carrinho);
    }
}
