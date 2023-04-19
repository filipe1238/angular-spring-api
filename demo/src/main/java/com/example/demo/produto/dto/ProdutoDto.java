package com.example.demo.produto.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class ProdutoDto {
    private UUID id;
    @NotNull(message = "First Name is required")
    private String descricao;
    private BigDecimal vrBruto;
    private BigDecimal vrDec;
    private BigDecimal vrLiq;
}
