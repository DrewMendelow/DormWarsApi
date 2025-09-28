package com.dormwars.dormwarsapi.controller;

import com.dormwars.dormwarsapi.model.Tournament;
import com.dormwars.dormwarsapi.repository.TournamentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    private final TournamentRepository repo;

    public TournamentController(TournamentRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Tournament> list() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Tournament> get(@PathVariable Long id) { return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }

    @PostMapping
    public Tournament create(@RequestBody Tournament tour) { return repo.save(tour); }

    @PutMapping("/{id}")
    public ResponseEntity<Tournament> update(@PathVariable Long id, @RequestBody Tournament in) {
        return repo.findById(id).map(existing -> { existing.setMaxTeams(in.getMaxTeams()); existing.setNumberOfRounds(in.getNumberOfRounds()); existing.setSport(in.getSport()); existing.setWinner(in.getWinner()); repo.save(existing); return ResponseEntity.ok(existing); }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { return repo.findById(id).map(existing -> { repo.delete(existing); return ResponseEntity.noContent().<Void>build(); }).orElse(ResponseEntity.notFound().build()); }
}
