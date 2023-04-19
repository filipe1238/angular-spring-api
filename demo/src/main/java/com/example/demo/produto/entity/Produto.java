package com.example.demo.produto.entity;

import com.example.demo.carrinho.entity.Carrinho;
import com.example.demo.utils.ParentEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "produto")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@RedisHash("Produto")
public class Produto extends ParentEntity {
    private String descricao;
    private BigDecimal vrBruto;
    private BigDecimal vrDesc;
    private BigDecimal vrLiq;
    @ManyToOne(cascade
            = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    private Carrinho carrinhoAtual;
    private String createdAt =
            new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z")
                    .format(new Date());
}
