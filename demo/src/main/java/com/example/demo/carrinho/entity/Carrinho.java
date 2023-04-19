package com.example.demo.carrinho.entity;

import com.example.demo.produto.entity.Produto;
import com.example.demo.utils.ParentEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.internal.util.collections.ArrayHelper;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "carrinho")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@RedisHash("Produto")
public class Carrinho extends ParentEntity {

    //relacao unidirecional sem cascade de delete
    private String descricao;
    @OneToMany(fetch = FetchType.LAZY,
             mappedBy = "carrinhoAtual")
    private List<Produto> produtos;
    private String createdAt =
            new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z")
                    .format(new Date());

    public BigDecimal getVrBrutoTotal() {
        if (produtos.isEmpty() || produtos == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal total = BigDecimal.ZERO;
        for (Produto prod : this.produtos) {
            total = total.add(prod.getVrBruto());
        }
        return total;
    }
    public BigDecimal getVrDescTotal() {
        if (produtos.isEmpty() || produtos == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal total = BigDecimal.ZERO;
        for (Produto prod : this.produtos) {
            total = total.add(prod.getVrDesc());
        }
        return total;
    }
    public BigDecimal getVrLiqTotal() {
        if (produtos.isEmpty() || produtos == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal total = BigDecimal.ZERO;
        for (Produto prod : this.produtos) {
            total = total.add(prod.getVrLiq());
        }
        return total;
    }
}
