package com.example.demo.utils;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@MappedSuperclass
@Getter @Setter
public class SuperClassDto {
    private Map<String, Object> changedAttrs = new HashMap<>();
}
