package com.example.demo.antiHero.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "anti_hero_entity")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@RedisHash("AntiHero")
public class AntiHeroEntity extends ParentEntity {
    @NotNull(message = "First Name is required")
    private String firstName;
    private String lastName;
    private String house;
    private String knownAs;
    private String createdAt =
            new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z")
                    .format(new Date());
}
