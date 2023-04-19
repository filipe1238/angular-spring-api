package com.example.demo.produto.service;

import com.example.demo.carrinho.entity.Carrinho;
import com.example.demo.exception.NotFoundException;
import com.example.demo.produto.entity.Produto;
import com.example.demo.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {
    private final ProdutoRepository repo;

    @Autowired
    public ProdutoService(ProdutoRepository repo) {
        this.repo = repo;
    }

    public Iterable<Produto> findAllProds() {
        return repo.findAll();
    }

    public Produto findProdById(UUID id) {
        return findOrThrow(id);
    }
    private Produto findOrThrow(final UUID id) {
        return repo
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Prod by id " + id + " was not found")
                        );
    }

    public void removeProdById(UUID id) {
        repo.deleteById(id);
    }

    public Produto addProd(
            Produto produto) {
        return repo.save(produto);
    }

    public void updateProd(UUID id,
                               Produto produto) {
        findOrThrow(id);
        repo.save(produto);
    }
    public List<Produto> findAllByCarrinhoAtualIn(Iterable<Carrinho> carr){
        return this.repo.findAllByCarrinhoAtualIn(carr);
    }
}
