package com.dormwars.dormwarsapi.controller;

import com.dormwars.dormwarsapi.model.*;
import com.dormwars.dormwarsapi.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventRepository repo;
    private final SportRepository sportRepo;
    private final EventTeamRepository eventTeamRepo;
    private final TournamentRepository tournamentRepo;

    public EventController(EventRepository repo,
                           SportRepository sportRepo,
                           EventTeamRepository eventTeamRepo,
                           TournamentRepository tournamentRepo) {
        this.repo = repo;
        this.sportRepo = sportRepo;
        this.eventTeamRepo = eventTeamRepo;
        this.tournamentRepo = tournamentRepo;
    }

    @GetMapping
    public List<Event> list() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Event> get(@PathVariable Long id) { return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EventRequest e) {
        if (e.getWinnerId() != null && e.getLoserId() != null
                && e.getWinnerId().equals(e.getLoserId())) {
            return ResponseEntity.badRequest().body("winner and loser cannot be the same team");
        }

        if (e.getSportId() == null) {
            return ResponseEntity.badRequest().body("sportId is required");
        }
        Optional<Sport> sportOpt = sportRepo.findById(e.getSportId());
        if (sportOpt.isEmpty()) return ResponseEntity.status(404).body("sport not found");

        Event ev = new Event();
        ev.setLocation(e.getLocation());
        ev.setDatetime(e.getDatetime());
        ev.setSport(sportOpt.get());               // set Sport entity resolved from sportId
        ev.setIsTournamentGame(e.getIsTournamentGame());
        ev.setStatus(e.getStatus());
        ev.setShortDescription(e.getShortDescription());
        ev.setLongDescription(e.getLongDescription());

        if (e.getWinnerId() != null) {
            var w = eventTeamRepo.findById(e.getWinnerId());
            if (w.isEmpty()) return ResponseEntity.status(404).body("winner event team not found");
            ev.setWinner(w.get());
        }
        if (e.getLoserId() != null) {
            var l = eventTeamRepo.findById(e.getLoserId());
            if (l.isEmpty()) return ResponseEntity.status(404).body("loser event team not found");
            ev.setLoser(l.get());
        }
        if (e.getTournamentId() != null) {
            var t = tournamentRepo.findById(e.getTournamentId());
            if (t.isEmpty()) return ResponseEntity.status(404).body("tournament not found");
            ev.setTournament(t.get());
        }

        ev.setRoundNumber(e.getRoundNumber());
        ev.setNextEventId(e.getNextEventId());
        ev.setEventName(e.getEventName());

        Event saved = repo.save(ev);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody EventRequest e) {
        // basic winner/loser validation
        if (e.getWinnerId() != null && e.getLoserId() != null && e.getWinnerId().equals(e.getLoserId())) {
            return ResponseEntity.badRequest().body("winner and loser cannot be the same team");
        }

        Optional<Event> existingOpt = repo.findById(id);
        if (existingOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Event existing = existingOpt.get();

        // sport (only update if provided)
        if (e.getSportId() != null) {
            Optional<Sport> sportOpt = sportRepo.findById(e.getSportId());
            if (sportOpt.isEmpty()) return ResponseEntity.status(404).body("sport not found");
            existing.setSport(sportOpt.get());
        }

        // map scalar fields (treat null as "no change")
        if (e.getLocation() != null) existing.setLocation(e.getLocation());
        if (e.getDatetime() != null) existing.setDatetime(e.getDatetime());
        if (e.getIsTournamentGame() != null) existing.setIsTournamentGame(e.getIsTournamentGame());
        if (e.getStatus() != null) existing.setStatus(e.getStatus());
        if (e.getShortDescription() != null) existing.setShortDescription(e.getShortDescription());
        if (e.getLongDescription() != null) existing.setLongDescription(e.getLongDescription());
        if (e.getRoundNumber() != null) existing.setRoundNumber(e.getRoundNumber());
        if (e.getNextEventId() != null) existing.setNextEventId(e.getNextEventId());
        if (e.getEventName() != null) existing.setEventName(e.getEventName());

        // winner
        if (e.getWinnerId() != null) {
            Optional<EventTeam> w = eventTeamRepo.findById(e.getWinnerId());
            if (w.isEmpty()) return ResponseEntity.status(404).body("winner event team not found");
            existing.setWinner(w.get());
        }

        // loser
        if (e.getLoserId() != null) {
            Optional<EventTeam> l = eventTeamRepo.findById(e.getLoserId());
            if (l.isEmpty()) return ResponseEntity.status(404).body("loser event team not found");
            existing.setLoser(l.get());
        }

        // tournament
        if (e.getTournamentId() != null) {
            Optional<Tournament> t = tournamentRepo.findById(e.getTournamentId());
            if (t.isEmpty()) return ResponseEntity.status(404).body("tournament not found");
            existing.setTournament(t.get());
        }

        repo.save(existing);
        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { return repo.findById(id).map(existing -> { repo.delete(existing); return ResponseEntity.noContent().<Void>build(); }).orElse(ResponseEntity.notFound().build()); }
}
