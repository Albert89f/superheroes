package com.example.superheros.config;

import com.example.superheros.models.HeroUser;
import com.example.superheros.repositories.HeroUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class InitializationConfig implements CommandLineRunner {

    final
    HeroUserRepository heroUserRepository;

    public InitializationConfig(HeroUserRepository heroUserRepository) {
        this.heroUserRepository = heroUserRepository;
    }

    @Override
    public void run(String... args) {

        heroUserRepository.save(new HeroUser("test", new BCryptPasswordEncoder().encode("test")));
    }
}
