package com.example.superheros.repositories;

import com.example.superheros.models.Superhero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuperheroRepository extends CrudRepository<Superhero, Long> {
    boolean existsByName(String name);
    List<Superhero> findAllByNameContains(String text);
}
