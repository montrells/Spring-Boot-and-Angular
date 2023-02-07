package com.example.springbootsuperheroes.superheroes.antiHero.service;

import java.util.UUID;

import com.example.springbootsuperheroes.superheroes.antiHero.entity.AntiHeroEntity;
import com.example.springbootsuperheroes.superheroes.exception.NotFoundException;
import com.example.springbootsuperheroes.superheroes.antiHero.repository.AntiHeroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AntiHeroService {

    private final AntiHeroRepository repo;
    public Iterable<AntiHeroEntity> findAllAntiHeroes() {
        //calls findAll method from the AntiHeroRepository, which returns an Iterable instance of AntiHeroEntity
        return repo.findAll();//from JPA...
    }

    public AntiHeroEntity findAntiHeroById(UUID id) {
        //calls the findById(id) from AntiHeroRepository, which returns a single AntiHeroEntity instance based on the Id value
        return findOrThrow(id); //Replaces the findById(id) from AntiHeroRepository with my custom exception
    }

    public void removeAntiHeroById(UUID id) {
        //calls the deletById(id) from AntiHeroRepository, which deletes a single AntiHeroEntity instance based on the Id value
        findOrThrow(id);//custom
        repo.deleteById(id);
    }

    public AntiHeroEntity addAntiHero(AntiHeroEntity antiHero) {
        //calls save() method from AntiHeroRepository and inserts a new AntiHeroEntity instance in the database
        return repo.save(antiHero);
    }

    public void updateAntiHero(UUID id, AntiHeroEntity antiHero) {
        //calls save(antiHero) method from AntiHeroRepository which updates a specific AntiHeroEntity instance in the database
        findOrThrow(id);//custom
        repo.save(antiHero);
    }
//Check if the ID was found on the database
    private AntiHeroEntity findOrThrow(final UUID id) {
        return repo
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Anti-hero by id " + id + " was not found")
                );
    }
}

