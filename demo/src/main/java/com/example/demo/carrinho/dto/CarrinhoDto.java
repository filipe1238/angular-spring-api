package com.example.demo.carrinho.dto;

import com.example.demo.produto.entity.Produto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CarrinhoDto {
    private UUID id;
    private List<Produto> produtos;
    private String desc;
}
