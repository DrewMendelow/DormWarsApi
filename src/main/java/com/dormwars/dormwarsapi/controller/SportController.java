package com.dormwars.dormwarsapi.controller;

import com.dormwars.dormwarsapi.model.Sport;
import com.dormwars.dormwarsapi.model.SportRequest;
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
    public ResponseEntity<?> create(@RequestBody SportRequest r) {
        Sport s = new Sport();
        s.setSportName(r.getSportName());
        s.setDescription(r.getDescription());
        s.setAbbreviation(r.getAbbreviation());
        s.setPlayersPerTeam(r.getPlayersPerTeam());
        Sport saved = repo.save(s);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody SportRequest r) {
        return repo.findById(id).map(existing -> {
            if (r.getSportName() != null) existing.setSportName(r.getSportName());
            if (r.getDescription() != null) existing.setDescription(r.getDescription());
            if (r.getAbbreviation() != null) existing.setAbbreviation(r.getAbbreviation());
            if (r.getPlayersPerTeam() != null) existing.setPlayersPerTeam(r.getPlayersPerTeam());
            repo.save(existing);
            return ResponseEntity.ok(existing);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return repo.findById(id).map(existing -> { repo.delete(existing); return ResponseEntity.noContent().<Void>build(); }).orElse(ResponseEntity.notFound().build());
    }
}
