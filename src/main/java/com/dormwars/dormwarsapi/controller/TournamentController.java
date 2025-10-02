package com.dormwars.dormwarsapi.controller;

import com.dormwars.dormwarsapi.model.Tournament;
import com.dormwars.dormwarsapi.model.TournamentRequest;
import com.dormwars.dormwarsapi.model.Sport;
import com.dormwars.dormwarsapi.model.EventTeam;
import com.dormwars.dormwarsapi.repository.TournamentRepository;
import com.dormwars.dormwarsapi.repository.SportRepository;
import com.dormwars.dormwarsapi.repository.EventTeamRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    private final TournamentRepository repo;
    private final SportRepository sportRepo;
    private final EventTeamRepository eventTeamRepo;

    public TournamentController(TournamentRepository repo, SportRepository sportRepo, EventTeamRepository eventTeamRepo) { this.repo = repo; this.sportRepo = sportRepo; this.eventTeamRepo = eventTeamRepo; }

    @GetMapping
    public List<Tournament> list() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Tournament> get(@PathVariable Long id) { return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TournamentRequest r) {
        if (r.getSportId() == null) return ResponseEntity.badRequest().body("sportId is required");
        Optional<Sport> s = sportRepo.findById(r.getSportId());
        if (s.isEmpty()) return ResponseEntity.status(404).body("sport not found");

        Tournament t = new Tournament();
        t.setSport(s.get());
        t.setMaxTeams(r.getMaxTeams());
        t.setNumberOfRounds(r.getNumberOfRounds());

        if (r.getWinnerId() != null) {
            Optional<EventTeam> w = eventTeamRepo.findById(r.getWinnerId());
            if (w.isEmpty()) return ResponseEntity.status(404).body("winner event team not found");
            t.setWinner(w.get());
        }

        Tournament saved = repo.save(t);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TournamentRequest r) {
        return repo.findById(id).map(existing -> {
            if (r.getSportId() != null) {
                Optional<Sport> s = sportRepo.findById(r.getSportId());
                if (s.isEmpty()) return ResponseEntity.status(404).body("sport not found");
                existing.setSport(s.get());
            }
            if (r.getMaxTeams() != null) existing.setMaxTeams(r.getMaxTeams());
            if (r.getNumberOfRounds() != null) existing.setNumberOfRounds(r.getNumberOfRounds());

            if (r.getWinnerId() != null) {
                Optional<EventTeam> w = eventTeamRepo.findById(r.getWinnerId());
                if (w.isEmpty()) return ResponseEntity.status(404).body("winner event team not found");
                existing.setWinner(w.get());
            }

            repo.save(existing);
            return ResponseEntity.ok(existing);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { return repo.findById(id).map(existing -> { repo.delete(existing); return ResponseEntity.noContent().<Void>build(); }).orElse(ResponseEntity.notFound().build()); }
}
