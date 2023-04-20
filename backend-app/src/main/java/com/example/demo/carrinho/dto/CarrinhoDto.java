package com.example.demo.carrinho.dto;

import com.example.demo.produto.entity.Produto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarrinhoDto {
    private UUID id;
    private String descricao;
    private List<Produto> produtos;

}
