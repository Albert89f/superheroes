package com.example.superheros.services;

import com.example.superheros.models.HeroUser;
import com.example.superheros.repositories.HeroUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    final
    HeroUserRepository heroUserRepository;

    public UserServiceImpl(HeroUserRepository heroUserRepository) {
        this.heroUserRepository = heroUserRepository;
    }

    @Override
    public void createNewUser(String username, String password) {
        heroUserRepository.save(new HeroUser(username, new BCryptPasswordEncoder().encode(password)));
    }
}
