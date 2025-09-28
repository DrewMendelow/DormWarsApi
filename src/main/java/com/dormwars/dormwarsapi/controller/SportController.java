package com.dormwars.dormwarsapi.controller;

import com.dormwars.dormwarsapi.model.Sport;
import com.dormwars.dormwarsapi.repository.SportRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sports")
public class SportController {

    private final SportRepository repo;

    public SportController(SportRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Sport> list() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Sport> get(@PathVariable Long id) { return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }

    @PostMapping
    public Sport create(@RequestBody Sport sport) { return repo.save(sport); }

    @PutMapping("/{id}")
    public ResponseEntity<Sport> update(@PathVariable Long id, @RequestBody Sport in) {
        return repo.findById(id).map(existing -> {
            existing.setSportName(in.getSportName());
            existing.setDescription(in.getDescription());
            existing.setAbbreviation(in.getAbbreviation());
            existing.setPlayersPerTeam(in.getPlayersPerTeam());
            repo.save(existing);
            return ResponseEntity.ok(existing);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return repo.findById(id).map(existing -> { repo.delete(existing); return ResponseEntity.noContent().<Void>build(); }).orElse(ResponseEntity.notFound().build());
    }
}
