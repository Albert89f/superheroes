package com.example.superheros.repositories;

import com.example.superheros.models.HeroUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroUserRepository extends CrudRepository<HeroUser, Long> {

    HeroUser findByUsername(String username);
}
