package com.example.demo.carrinho.controller;

import com.example.demo.carrinho.dto.CarrinhoDto;
import com.example.demo.carrinho.entity.Carrinho;
import com.example.demo.carrinho.service.CarrinhoService;
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

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@RestController
@CrossOrigin(allowedHeaders = "Content-type")
@RequestMapping("api/v1/carrinhos")
public class CarrinhoController {
    private final CarrinhoService service;
    private final ModelMapper mapper;
    @Autowired
    private ObjectMapper objectMapper;
    private CarrinhoDto convertToDto(Carrinho entity) {
        return mapper.map(entity, CarrinhoDto.class);
    }

    private Carrinho convertToEntity(CarrinhoDto dto) {
        return mapper.map(dto, Carrinho.class);
    }

    @GetMapping("/{id}")
    public CarrinhoDto getCarrinhoById(@PathVariable("id") UUID id) {
        return convertToDto(service.findCarrinhoById(id));
    }

    @PostMapping
    public CarrinhoDto postCarrinho(@Valid @RequestBody CarrinhoDto produtoDto) {
        var entity = convertToEntity(produtoDto);
        var prod = service.addCarrinho(entity);
        return convertToDto(prod);
    }

    @PutMapping("/{id}")
    public void putCarrinho(
            @PathVariable("id") UUID id,
            @Valid @RequestBody CarrinhoDto carrinhoDto
    ) throws JsonMappingException {
        if (!id.equals(carrinhoDto.getId())) throw new
                ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "id does not match."
        );
        Carrinho carrPersist = service.findCarrinhoById(id);
        Carrinho produto = objectMapper.updateValue(carrPersist, carrinhoDto);
//        var prod = convertToEntity(produtoDto);
        service.updateCarrinho(id, produto);
    }

    @DeleteMapping("/{id}")
    public void deleteCarrinhoById(@PathVariable("id") UUID id) {
        service.removeCarrinhoById(id);
    }

//    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public List<CarrinhoDto> getCarrinho(Pageable pageable) {
        int toSkip = pageable.getPageSize() *
                pageable.getPageNumber();
        var prodList = StreamSupport
                .stream(service.findAllCarrinhos().spliterator(), false)
                .skip(toSkip).limit(pageable.getPageSize())
                .collect(Collectors.toList());
        return prodList
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
