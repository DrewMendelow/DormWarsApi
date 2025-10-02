package com.dormwars.dormwarsapi.controller;

import com.dormwars.dormwarsapi.model.Team;
import com.dormwars.dormwarsapi.model.TeamRequest;
import com.dormwars.dormwarsapi.model.School;
import com.dormwars.dormwarsapi.repository.TeamRepository;
import com.dormwars.dormwarsapi.repository.SchoolRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamRepository repo;
    private final SchoolRepository schoolRepo;

    public TeamController(TeamRepository repo, SchoolRepository schoolRepo) { this.repo = repo; this.schoolRepo = schoolRepo; }

    @GetMapping
    public List<Team> list() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Team> get(@PathVariable Long id) { return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TeamRequest r) {
        Team team = new Team();
        team.setTeamName(r.getTeamName());
        team.setActive(r.getActive() != null ? r.getActive() : true);

        if (r.getSchoolId() != null) {
            Optional<School> s = schoolRepo.findById(r.getSchoolId());
            if (s.isEmpty()) return ResponseEntity.status(404).body("school not found");
            team.setSchool(s.get());
        } else {
            team.setSchool(null);
        }

        Team saved = repo.save(team);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TeamRequest r) {
        return repo.findById(id).map(existing -> {
            if (r.getTeamName() != null) existing.setTeamName(r.getTeamName());
            if (r.getActive() != null) existing.setActive(r.getActive());

            if (r.getSchoolId() != null) {
                Optional<School> s = schoolRepo.findById(r.getSchoolId());
                if (s.isEmpty()) return ResponseEntity.status(404).body("school not found");
                existing.setSchool(s.get());
            }

            repo.save(existing);
            return ResponseEntity.ok(existing);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { return repo.findById(id).map(existing -> { repo.delete(existing); return ResponseEntity.noContent().<Void>build(); }).orElse(ResponseEntity.notFound().build()); }
}
