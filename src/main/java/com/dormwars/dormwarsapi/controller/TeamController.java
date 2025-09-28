package com.dormwars.dormwarsapi.controller;

import com.dormwars.dormwarsapi.model.Team;
import com.dormwars.dormwarsapi.model.School;
import com.dormwars.dormwarsapi.repository.TeamRepository;
import com.dormwars.dormwarsapi.repository.SchoolRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Team create(@RequestBody Team team) {
        if (team.getSchool() != null && team.getSchool().getSchoolId() != null) {
            School s = schoolRepo.findById(team.getSchool().getSchoolId()).orElse(null);
            team.setSchool(s);
        }
        return repo.save(team);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> update(@PathVariable Long id, @RequestBody Team in) {
        return repo.findById(id).map(existing -> {
            existing.setTeamName(in.getTeamName());
            if (in.getSchool() != null && in.getSchool().getSchoolId() != null) {
                School s = schoolRepo.findById(in.getSchool().getSchoolId()).orElse(null);
                existing.setSchool(s);
            }
            existing.setActive(in.getActive());
            repo.save(existing);
            return ResponseEntity.ok(existing);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { return repo.findById(id).map(existing -> { repo.delete(existing); return ResponseEntity.noContent().<Void>build(); }).orElse(ResponseEntity.notFound().build()); }
}
