package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/workintech/animal")
public class AnimalController {

    @Value("${course.name}")
    private String courseName;

    @Value("${project.developer.fullname}")
    private String developerFullname;

    private final Map<Integer, Animal> animals = new HashMap<>();

    public AnimalController() {

        animals.put(100, new Animal(100, "default"));
    }

    @GetMapping
    public List<Animal> getAllAnimals() {
        return new ArrayList<>(animals.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable Integer id) {
        Animal animal = animals.get(id);
        if (animal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(animal);
    }

    @PostMapping
    public ResponseEntity<Animal> addAnimal(@RequestBody Animal animal) {
        if (animal == null || animal.getId() == null || animal.getName() == null) {
            return ResponseEntity.badRequest().build();
        }
        animals.put(animal.getId(), animal);
        return ResponseEntity.ok(animal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> updateAnimal(@PathVariable Integer id, @RequestBody Animal animal) {
        if (animal == null || animal.getId() == null || animal.getName() == null) {
            return ResponseEntity.badRequest().build();
        }

        animals.remove(id);
        animals.put(animal.getId(), animal);

        return ResponseEntity.ok(animal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnimal(@PathVariable Integer id) {
        animals.remove(id);
        return ResponseEntity.ok("deleted");
    }
}