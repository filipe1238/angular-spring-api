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
@RequestMapping("api/v1/prods")
public class ProdutoController {
    private final ProdutoService service;
    private final ModelMapper mapper;

    private ProdutoDto convertToDto(Produto entity) {
        return mapper.map(entity, ProdutoDto.class);
    }

    private Produto convertToEntity(ProdutoDto dto) {
        return mapper.map(dto, Produto.class);
    }

    @GetMapping("/{id}")
    public ProdutoDto getProdById(@PathVariable("id") UUID id) {
        return convertToDto(service.findProdById(id));
    }

    @PostMapping
    public ProdutoDto postProd(@Valid @RequestBody ProdutoDto produtoDto) {
        var entity = convertToEntity(produtoDto);
        var prod = service.addProd(entity);
        return convertToDto(prod);
    }

    @PutMapping("/{id}")
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

    @DeleteMapping("/{id}")
    public void deleteProdById(@PathVariable("id") UUID id) {
        service.removeProdById(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
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
