package com.example.demo.produto.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@MappedSuperclass
@Getter @Setter
public class SuperClassDtoProd {
    private Map<String, Object> changedAttrs = new HashMap<>();
}
