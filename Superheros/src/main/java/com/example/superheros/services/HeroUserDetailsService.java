package com.example.superheros.services;

import com.example.superheros.models.HeroUser;
import com.example.superheros.repositories.HeroUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class HeroUserDetailsService implements UserDetailsService {

    final HeroUserRepository heroUserRepository;

    public HeroUserDetailsService(HeroUserRepository heroUserRepository) {
        this.heroUserRepository = heroUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HeroUser user = heroUserRepository.findByUsername(username);
        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
