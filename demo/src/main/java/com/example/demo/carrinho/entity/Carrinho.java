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
@Table(name = "produto")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@RedisHash("Produto")
public class Carrinho extends ParentEntity {

    //relacao unidirecional sem cascade de delete
    @OneToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH})
    private List<Produto> listaProdutos;
    private String descricao;
    private String createdAt =
            new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z")
                    .format(new Date());

    public BigDecimal getVrBrutoTotal() {
        if (listaProdutos.isEmpty() || listaProdutos == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal total = BigDecimal.ZERO;
        for (Produto prod : this.listaProdutos) {
            total = total.add(prod.getVrBruto());
        }
        return total;
    }
    public BigDecimal getVrDescTotal() {
        if (listaProdutos.isEmpty() || listaProdutos == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal total = BigDecimal.ZERO;
        for (Produto prod : this.listaProdutos) {
            total = total.add(prod.getVrDesc());
        }
        return total;
    }
    public BigDecimal getVrLiqTotal() {
        if (listaProdutos.isEmpty() || listaProdutos == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal total = BigDecimal.ZERO;
        for (Produto prod : this.listaProdutos) {
            total = total.add(prod.getVrLiq());
        }
        return total;
    }
}
