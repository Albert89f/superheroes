package com.example.superheros.services;

import com.example.superheros.models.Superhero;
import com.example.superheros.repositories.SuperheroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class HeroServiceImpl implements HeroService {

    final SuperheroRepository superheroRepository;

    public HeroServiceImpl(SuperheroRepository superheroRepository) {
        this.superheroRepository = superheroRepository;
    }

    @Override
    public Superhero createHero(String name) {
        if (superheroRepository.existsByName(name)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Superhero already exists!");
        }
        Superhero hero = new Superhero(name);
        superheroRepository.save(hero);
        return hero;
    }

    @Override
    public Superhero modifyHero(Long id, String name) {
        Optional<Superhero> hero = superheroRepository.findById(id);
        if (hero.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Superhero doesn't exists!");
        } else if (superheroRepository.existsByName(name)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Superhero already exists with this name!");
        } else {
            Superhero result = hero.get();
            result.setName(name);
            return superheroRepository.save(result);
        }
    }

    @Override
    public void deleteHero(Long id) {
        Optional<Superhero> hero = superheroRepository.findById(id);
        if (hero.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Superhero doesn't exists!");
        } else {
            Superhero result = hero.get();
            superheroRepository.delete(result);
        }
    }

    @Override
    public Superhero listHero(Long id) {
        Optional<Superhero> hero = superheroRepository.findById(id);
        if (hero.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Superhero doesn't exists!");
        } else {
            return hero.get();
        }
    }

    @Override
    public List<Superhero> listAllHeroes() {
        return (List<Superhero>) superheroRepository.findAll();
    }

    @Override
    public List<Superhero> listAllHeroesByName(String name) {
        return (List<Superhero>) superheroRepository.findAllByNameContains(name);
    }
}
