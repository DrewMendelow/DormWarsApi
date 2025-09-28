package com.dormwars.dormwarsapi.controller;

import com.dormwars.dormwarsapi.model.EventTeam;
import com.dormwars.dormwarsapi.repository.EventTeamRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event-teams")
public class EventTeamController {

    private final EventTeamRepository repo;

    public EventTeamController(EventTeamRepository repo) { this.repo = repo; }

    @GetMapping
    public List<EventTeam> list() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<EventTeam> get(@PathVariable Long id) { return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }

    @PostMapping
    public EventTeam create(@RequestBody EventTeam et) { return repo.save(et); }

    @PutMapping("/{id}")
    public ResponseEntity<EventTeam> update(@PathVariable Long id, @RequestBody EventTeam in) {
        return repo.findById(id).map(existing -> { existing.setEventId(in.getEventId()); existing.setTeam(in.getTeam()); existing.setIsTournamentTeam(in.getIsTournamentTeam()); existing.setTournament(in.getTournament()); existing.setActive(in.getActive()); repo.save(existing); return ResponseEntity.ok(existing); }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { return repo.findById(id).map(existing -> { repo.delete(existing); return ResponseEntity.noContent().<Void>build(); }).orElse(ResponseEntity.notFound().build()); }
}
