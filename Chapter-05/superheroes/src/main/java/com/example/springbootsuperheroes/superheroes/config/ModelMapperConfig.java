package com.example.springbootsuperheroes.superheroes.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    //This bean will return a new instance of ModelMapper.
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
