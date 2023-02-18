package com.example.superheros.services;

import com.example.superheros.models.Superhero;

import java.util.List;

public interface HeroService {

    Superhero createHero(String name);

    Superhero modifyHero(Long id, String name);

    void deleteHero(Long id);

    Superhero listHero(Long id);

    List<Superhero> listAllHeroes();

    List<Superhero> listAllHeroesByName(String name);
}
