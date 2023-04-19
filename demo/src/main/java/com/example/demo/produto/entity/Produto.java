package com.example.demo.produto.entity;

import com.example.demo.carrinho.entity.Carrinho;
import com.example.demo.utils.ParentEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    @JsonIgnoreProperties("produtos")
    @ManyToMany(fetch = FetchType.LAZY,
            cascade
            = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "carrinho_id"))
    private List<Carrinho> carrinhoAtual;
    private String createdAt =
            new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z")
                    .format(new Date());
}
