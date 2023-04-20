package com.example.demo.produto.controller;

import com.example.demo.carrinho.entity.Carrinho;
import com.example.demo.carrinho.service.CarrinhoService;
import com.example.demo.produto.dto.ProdutoDto;
import com.example.demo.produto.entity.Produto;
import com.example.demo.produto.service.ProdutoService;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@RestController
@CrossOrigin(allowedHeaders = "Content-type")
@RequestMapping("api/v1/")
public class ProdutoController {
    private final ProdutoService service;
    private final CarrinhoService serviceCarrinho;
    private final ModelMapper mapper;
    @Autowired
    private ObjectMapper objectMapper;

    private ProdutoDto convertToDto(Produto entity) {
        return mapper.map(entity, ProdutoDto.class);
    }

    private Produto convertToEntity(ProdutoDto dto) {
        return mapper.map(dto, Produto.class);
    }

    //Get Mapps
    @GetMapping("/carrinhos/{carrinhoId}/prods/{prodId}")
    public ProdutoDto getProdByIdInCarrinho(@PathVariable("prodId") UUID prodId,
                                            @PathVariable("carrinhoId") UUID carrinhoId) {
        Carrinho carrinhoFiltrado = serviceCarrinho.findCarrinhoById(carrinhoId);
        Produto produtoAchado = null;
        for (Produto prod : carrinhoFiltrado.getProdutos()) {
            if (prod.getId().equals(prodId)) {
                produtoAchado = prod;
            }
        }
        return convertToDto(produtoAchado);
    }
    @GetMapping("/carrinhos/{carrinhoId}/prods")
    public List<ProdutoDto> getProdByIdInCarrinho(@PathVariable("carrinhoId") UUID carrinhoId) {
        Carrinho carrinhoFiltrado = serviceCarrinho.findCarrinhoById(carrinhoId);
        List<ProdutoDto> produtosAchadosDto = new ArrayList<>();
        for(Produto prod : carrinhoFiltrado.getProdutos()){
            produtosAchadosDto.add(this.convertToDto(prod));
        }
        return produtosAchadosDto;
    }

    @GetMapping("/prods/{prodId}")
    public ProdutoDto getProdById(@PathVariable("prodId") UUID prodId) {

        return convertToDto(service.findProdById(prodId));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/prods")
    public List<ProdutoDto> getProd(Pageable pageable) {
        int toSkip = pageable.getPageSize() *
                pageable.getPageNumber();
        var prodList = StreamSupport
                .stream(service.findAllProds().spliterator(), false)
                .skip(toSkip).limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return prodList
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
//Post Mapps
    @PostMapping("/carrinhos/{carrinhoId}/prods")
    public ProdutoDto postProdInCarrinho(@Valid @RequestBody ProdutoDto produtoDto) {
        var entity = convertToEntity(produtoDto);
        var prod = service.addProd(entity);
        return convertToDto(prod);
    }

    @PostMapping("/prods")
    public ProdutoDto postProd(@Valid @RequestBody ProdutoDto produtoDto) {
        var entity = convertToEntity(produtoDto);
        var prod = service.addProd(entity);
        return convertToDto(prod);
    }

    @PutMapping("/prods")
    public ProdutoDto postProd(@Valid @RequestBody Produto produtoDto) {
//        var entity = convertToEntity(produtoDto);
        var prod = service.addProd(produtoDto);
        return convertToDto(prod);
    }
//Put Mapps
    @PutMapping("/carrinhos/{carrinhoId}/prods/{prodId}")
    public void putProdInCarrinho(
            @PathVariable("id") UUID id,
            @Valid @RequestBody ProdutoDto produtoDto
    ) throws JsonMappingException {
        if (!id.equals(produtoDto.getId())) throw new
                ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "id does not match."
        );
        Produto prod = service.findProdById(id);
        Produto produto = objectMapper.updateValue(prod, produtoDto);

//        var prod = convertToEntity(produtoDto);
        service.updateProd(produto);
    }

    @PutMapping("/prods/{prodId}")
    public void putProdtest(
            @PathVariable("prodId") UUID id,
            @Valid @RequestBody ProdutoDto produtoDto
    ) throws JsonMappingException {
        if (!id.equals(produtoDto.getId())) throw new
                ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "id does not match."
        );
        Produto prod = service.findProdById(id);
        Produto produto = objectMapper.updateValue(prod, produtoDto);
        service.updateProd(produto);
    }
//Delete Mapps
    @DeleteMapping("/prods/{prodId}")
    public void deleteProdById(@PathVariable("prodId") UUID prodId) {
        service.removeProdById(prodId);
    }


}
