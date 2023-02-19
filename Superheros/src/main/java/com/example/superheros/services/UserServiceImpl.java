package com.example.superheros.services;

import com.example.superheros.models.HeroUser;
import com.example.superheros.repositories.HeroUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

    final
    HeroUserRepository heroUserRepository;

    public UserServiceImpl(HeroUserRepository heroUserRepository) {
        this.heroUserRepository = heroUserRepository;
    }

    @Override
    public void createNewUser(String username, String password) {

        if (heroUserRepository.existsByUsername(username)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists with this name!");
        }
        heroUserRepository.save(new HeroUser(username, new BCryptPasswordEncoder().encode(password)));
    }
}
