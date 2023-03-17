package com.example.springbootsuperheroes.superheroes.antiHero.dto.controller;

import com.example.springbootsuperheroes.superheroes.antiHero.dto.AntiHeroDto;
import com.example.springbootsuperheroes.superheroes.antiHero.entity.AntiHeroEntity;
import com.example.springbootsuperheroes.superheroes.antiHero.service.AntiHeroService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/anti-heroes")
public class AntiHeroController {
    private final AntiHeroService service;  //This is where all the CRUD lodgic is located
    private final ModelMapper mapper;

    @GetMapping
    public List<AntiHeroDto> getAntiHeroes() {
        // Mapstruct is another dto mapper, but it's not straight forward
        var antiHeroList = StreamSupport
                .stream(service.findAllAntiHeroes().spliterator(), false)
                .collect(Collectors.toList());


        return antiHeroList
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AntiHeroDto getAntiHeroById(@PathVariable("id") UUID id) {
        return convertToDto(service.findAntiHeroById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteAntiHeroById(@PathVariable("id") UUID id) {
        service.removeAntiHeroById(id);
    }

    @PostMapping
    public AntiHeroDto postAntiHero(@Valid @RequestBody AntiHeroDto antiHeroDto) {
        var entity = convertToEntity(antiHeroDto);//converts and object into an entity
        var antiHero = service.addAntiHero(entity);//method to insert the converted entity into the database.

        return convertToDto(antiHero);
    }

    @PutMapping("/{id}")
    public void putAntiHero(
            @PathVariable("id") UUID id,
            @Valid @RequestBody AntiHeroDto antiHeroDto
    ) {
        if (!id.equals(antiHeroDto.getId())) throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "id does not match"
        );

        var antiHeroEntity = convertToEntity(antiHeroDto);
        service.updateAntiHero(id, antiHeroEntity);
    }

    //Mappings for out CRUD methods... mapper and service injected above
//Converts the entity to a dto
    private AntiHeroDto convertToDto(AntiHeroEntity entity) {
        return mapper.map(entity, AntiHeroDto.class);
    }
//Converts the dto to an entity
    private AntiHeroEntity convertToEntity(AntiHeroDto dto) {
        return mapper.map(dto, AntiHeroEntity.class);
    }

}
