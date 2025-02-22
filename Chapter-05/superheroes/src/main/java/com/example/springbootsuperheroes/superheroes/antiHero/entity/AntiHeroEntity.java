package com.example.springbootsuperheroes.superheroes.antiHero.entity;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class AntiHeroEntity {

    @Id //primarykey
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID") //primarykey can be generated here or on the database directly  -The repository interface also uses the primary key
    @Column(nullable = false, updatable = false)
    private UUID id;

    @NotNull(message = "First Name is required")
    private String firstName;

    private String lastName;
    private String house;
    private String knownAs;
    private String createdAt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z")
            .format(new Date());
}
