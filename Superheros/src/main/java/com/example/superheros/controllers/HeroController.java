package com.example.superheros.controllers;

import com.example.superheros.models.Superhero;
import com.example.superheros.services.HeroService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeroController {

    final HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @PostMapping("/hero/new")
    public ResponseEntity<Superhero> createHero(@RequestParam @NotBlank String name) {
        return ResponseEntity.status(HttpStatus.CREATED).body(heroService.createHero(name));
    }
}
