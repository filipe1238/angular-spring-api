package com.example.demo.antiHero.repository;

import com.example.demo.antiHero.entity.AntiHeroEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AntiHeroRepository extends CrudRepository<AntiHeroEntity, UUID> {
}
