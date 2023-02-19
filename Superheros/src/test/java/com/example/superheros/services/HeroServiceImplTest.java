package com.example.superheros.services;

import com.example.superheros.repositories.SuperheroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

class HeroServiceImplTest {

    @Test
    void createHeroAndSave() {

        var mockSuperheroRepository = Mockito.mock(SuperheroRepository.class);

        HeroService heroService = new HeroServiceImpl(mockSuperheroRepository);

        heroService.createHero("Superman");

        Mockito.verify(mockSuperheroRepository, Mockito.times(1)).save(Mockito.any());

    }

    @Test
    void createHeroWithExistingName() {

        var mockSuperheroRepository = Mockito.mock(SuperheroRepository.class);
        Mockito.when(mockSuperheroRepository.existsByName("Superman")).thenReturn(true);

        HeroService heroService = new HeroServiceImpl(mockSuperheroRepository);
        
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            heroService.createHero("Superman");
        });

        Assertions.assertEquals("400 BAD_REQUEST \"Superhero already exists!\"", exception.getMessage());
    }
}