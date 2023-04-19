package com.example.demo.produto.controller;

import com.example.demo.produto.dto.ProdutoDto;
import com.example.demo.produto.entity.Produto;
import com.example.demo.produto.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    private final ModelMapper mapper;

    private ProdutoDto convertToDto(Produto entity) {
        return mapper.map(entity, ProdutoDto.class);
    }

    private Produto convertToEntity(ProdutoDto dto) {
        return mapper.map(dto, Produto.class);
    }

    @GetMapping("/carrinhos/{carrinhoId}/prod/{prodId}")
    public ProdutoDto getProdById(@PathVariable("prodId") UUID prodId,
                                  @PathVariable("carrinhoId") UUID carrinhoId) {

        return convertToDto(service.findProdById(prodId));
    }

    @PostMapping("/carrinhos/{carrinhoId}/prod")
    public ProdutoDto postProd(@Valid @RequestBody ProdutoDto produtoDto) {
        var entity = convertToEntity(produtoDto);
        var prod = service.addProd(entity);
        return convertToDto(prod);
    }

    @PutMapping("/carrinhos/{carrinhoId}/prod/{prodId}")
    public void putProd(
            @PathVariable("id") UUID id,
            @Valid @RequestBody ProdutoDto produtoDto
    ) {
        if (!id.equals(produtoDto.getId())) throw new
                ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "id does not match."
        );
        var prod = convertToEntity(produtoDto);
        service.updateProd(id, prod);
    }

    @DeleteMapping("/carrinhos/{carrinhoId}/prod/{prodId}")
    public void deleteProdById(@PathVariable("prodId") UUID prodId,
                               @PathVariable("carrinhoId") UUID carrinhoId) {
        service.removeProdById(prodId);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/prod")
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
}
