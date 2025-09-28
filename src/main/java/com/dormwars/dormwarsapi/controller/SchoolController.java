package com.dormwars.dormwarsapi.controller;

import com.dormwars.dormwarsapi.model.School;
import com.dormwars.dormwarsapi.repository.SchoolRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {

    private final SchoolRepository repo;

    public SchoolController(SchoolRepository repo) { this.repo = repo; }

    @GetMapping
    public List<School> list() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<School> get(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public School create(@RequestBody School school) { return repo.save(school); }

    @PutMapping("/{id}")
    public ResponseEntity<School> update(@PathVariable Long id, @RequestBody School in) {
        return repo.findById(id).map(existing -> {
            existing.setSchoolName(in.getSchoolName());
            existing.setCity(in.getCity());
            existing.setState(in.getState());
            existing.setPrimaryColor(in.getPrimaryColor());
            existing.setSecondaryColor(in.getSecondaryColor());
            existing.setSchoolLogo(in.getSchoolLogo());
            existing.setActive(in.getActive());
            repo.save(existing);
            return ResponseEntity.ok(existing);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return repo.findById(id).map(existing -> {
            repo.delete(existing);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
