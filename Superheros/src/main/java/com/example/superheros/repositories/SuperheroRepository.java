package com.example.superheros.repositories;

import com.example.superheros.models.Superhero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperheroRepository extends CrudRepository<Superhero, Long> {
}
