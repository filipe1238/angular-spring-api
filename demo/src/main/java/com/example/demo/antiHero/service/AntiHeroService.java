package com.example.demo.antiHero.service;

import com.example.demo.antiHero.entity.AntiHeroEntity;
import com.example.demo.antiHero.repository.AntiHeroRepository;
import com.example.demo.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AntiHeroService {
    private final AntiHeroRepository repo;

    @Autowired
    public AntiHeroService(AntiHeroRepository repo) {
        this.repo = repo;
    }

    public Iterable<AntiHeroEntity> findAllAntiHeroes() {
        return repo.findAll();
    }

    public AntiHeroEntity findAntiHeroById(UUID id) {
        return findOrThrow(id);
    }
    private AntiHeroEntity findOrThrow(final UUID id) {
        return repo
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Anti-hero by id " + id + " was not found")
                        );
    }

    public void removeAntiHeroById(UUID id) {
        repo.deleteById(id);
    }

    public AntiHeroEntity addAntiHero(
            AntiHeroEntity antiHero) {
        return repo.save(antiHero);
    }

    public void updateAntiHero(UUID id,
                               AntiHeroEntity antiHero) {
        findOrThrow(id);
        repo.save(antiHero);
    }
}
