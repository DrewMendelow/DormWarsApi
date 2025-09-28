package com.dormwars.dormwarsapi.controller;

import com.dormwars.dormwarsapi.model.Event;
import com.dormwars.dormwarsapi.repository.EventRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventRepository repo;

    public EventController(EventRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Event> list() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Event> get(@PathVariable Long id) { return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Event e) {
        if (e.getWinner() != null && e.getLoser() != null && e.getWinner().getEventTeamId() != null && e.getLoser().getEventTeamId() != null
                && e.getWinner().getEventTeamId().equals(e.getLoser().getEventTeamId())) {
            return ResponseEntity.badRequest().body("winner and loser cannot be the same team");
        }
        Event saved = repo.save(e);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Event in) {
        if (in.getWinner() != null && in.getLoser() != null && in.getWinner().getEventTeamId() != null && in.getLoser().getEventTeamId() != null
                && in.getWinner().getEventTeamId().equals(in.getLoser().getEventTeamId())) {
            return ResponseEntity.badRequest().body("winner and loser cannot be the same team");
        }
        return repo.findById(id).map(existing -> {
            existing.setLocation(in.getLocation());
            existing.setDatetime(in.getDatetime());
            existing.setSport(in.getSport());
            existing.setIsTournamentGame(in.getIsTournamentGame());
            existing.setStatus(in.getStatus());
            existing.setShortDescription(in.getShortDescription());
            existing.setLongDescription(in.getLongDescription());
            existing.setWinner(in.getWinner());
            existing.setLoser(in.getLoser());
            existing.setTournament(in.getTournament());
            existing.setRoundNumber(in.getRoundNumber());
            existing.setNextEvent(in.getNextEvent());
            repo.save(existing);
            return ResponseEntity.ok(existing);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { return repo.findById(id).map(existing -> { repo.delete(existing); return ResponseEntity.noContent().<Void>build(); }).orElse(ResponseEntity.notFound().build()); }
}
