package com.example.demo.antiHero.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@MappedSuperclass
public abstract class ParentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//already default, optional
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Boolean isTransient() {
        if (this.id == null || this.id.equals(0)) {
            return true;
        }
        return false;
    }
}
