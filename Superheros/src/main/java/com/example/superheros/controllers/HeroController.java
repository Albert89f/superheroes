package com.example.superheros.controllers;

import com.example.superheros.models.Superhero;
import com.example.superheros.services.HeroService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HeroController {

    final HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @PutMapping("/hero/new")
    public ResponseEntity<Superhero> createHero(@RequestParam @NotBlank String name) {
        return ResponseEntity.status(HttpStatus.CREATED).body(heroService.createHero(name));
    }

    @PostMapping("/hero/modify/{id}")
    public ResponseEntity<Superhero> modifyHero(@RequestParam @NotBlank String name, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(heroService.modifyHero(id, name));
    }

    @DeleteMapping("/hero/delete/{id}")
    public ResponseEntity<?> deleteHero(@PathVariable Long id) {
        heroService.deleteHero(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/hero/list/{id}")
    public ResponseEntity<Superhero> listHero(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(heroService.listHero(id));
    }

    @GetMapping("/hero/list/all")
    public ResponseEntity<List<Superhero>> listAllHeroes() {
        return ResponseEntity.status(HttpStatus.OK).body(heroService.listAllHeroes());
    }

    @GetMapping("/hero/list/all/name")
    public ResponseEntity<List<Superhero>> listHero(@RequestParam @NotBlank String name) {
        return ResponseEntity.status(HttpStatus.OK).body(heroService.listAllHeroesByName(name));
    }
}
