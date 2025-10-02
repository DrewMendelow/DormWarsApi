package com.dormwars.dormwarsapi.controller;

import com.dormwars.dormwarsapi.model.EventTeam;
import com.dormwars.dormwarsapi.model.EventTeamRequest;
import com.dormwars.dormwarsapi.model.Team;
import com.dormwars.dormwarsapi.model.Tournament;
import com.dormwars.dormwarsapi.repository.EventTeamRepository;
import com.dormwars.dormwarsapi.repository.TeamRepository;
import com.dormwars.dormwarsapi.repository.TournamentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/event-teams")
public class EventTeamController {

    private final EventTeamRepository repo;
    private final TeamRepository teamRepo;
    private final TournamentRepository tournamentRepo;

    public EventTeamController(EventTeamRepository repo, TeamRepository teamRepo, TournamentRepository tournamentRepo) {
        this.repo = repo;
        this.teamRepo = teamRepo;
        this.tournamentRepo = tournamentRepo;
    }

    @GetMapping
    public List<EventTeam> list() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<EventTeam> get(@PathVariable Long id) { return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EventTeamRequest r) {
        EventTeam et = new EventTeam();
        et.setEventId(r.getEventId());

        if (r.getTeamId() == null) return ResponseEntity.badRequest().body("teamId is required");
        Optional<Team> t = teamRepo.findById(r.getTeamId());
        if (t.isEmpty()) return ResponseEntity.status(404).body("team not found");
        et.setTeam(t.get());

        et.setIsTournamentTeam(r.getIsTournamentTeam());

        if (r.getTournamentId() != null) {
            Optional<Tournament> tr = tournamentRepo.findById(r.getTournamentId());
            if (tr.isEmpty()) return ResponseEntity.status(404).body("tournament not found");
            et.setTournament(tr.get());
        }

        et.setActive(r.getActive() != null ? r.getActive() : true);

        EventTeam saved = repo.save(et);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody EventTeamRequest r) {
        return repo.findById(id).map(existing -> {
            if (r.getEventId() != null) existing.setEventId(r.getEventId());

            if (r.getTeamId() != null) {
                Optional<Team> t = teamRepo.findById(r.getTeamId());
                if (t.isEmpty()) return ResponseEntity.status(404).body("team not found");
                existing.setTeam(t.get());
            }

            if (r.getIsTournamentTeam() != null) existing.setIsTournamentTeam(r.getIsTournamentTeam());

            if (r.getTournamentId() != null) {
                Optional<Tournament> tr = tournamentRepo.findById(r.getTournamentId());
                if (tr.isEmpty()) return ResponseEntity.status(404).body("tournament not found");
                existing.setTournament(tr.get());
            }

            if (r.getActive() != null) existing.setActive(r.getActive());

            repo.save(existing);
            return ResponseEntity.ok(existing);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { return repo.findById(id).map(existing -> { repo.delete(existing); return ResponseEntity.noContent().<Void>build(); }).orElse(ResponseEntity.notFound().build()); }
}
