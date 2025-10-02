package com.dormwars.dormwarsapi.controller;

import com.dormwars.dormwarsapi.model.School;
import com.dormwars.dormwarsapi.model.SchoolRequest;
import com.dormwars.dormwarsapi.repository.SchoolRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {

    private final SchoolRepository repo;

    public SchoolController(SchoolRepository repo) { this.repo = repo; }

    @GetMapping
    public List<School> list() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<School> get(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody SchoolRequest r) {
        School s = new School();
        s.setSchoolName(r.getSchoolName());
        s.setCity(r.getCity());
        s.setState(r.getState());
        s.setPrimaryColor(r.getPrimaryColor());
        s.setSecondaryColor(r.getSecondaryColor());
        s.setSchoolLogo(r.getSchoolLogo());
        s.setActive(r.getActive() != null ? r.getActive() : true);
        School saved = repo.save(s);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody SchoolRequest r) {
        return repo.findById(id).map(existing -> {
            if (r.getSchoolName() != null) existing.setSchoolName(r.getSchoolName());
            if (r.getCity() != null) existing.setCity(r.getCity());
            if (r.getState() != null) existing.setState(r.getState());
            if (r.getPrimaryColor() != null) existing.setPrimaryColor(r.getPrimaryColor());
            if (r.getSecondaryColor() != null) existing.setSecondaryColor(r.getSecondaryColor());
            if (r.getSchoolLogo() != null) existing.setSchoolLogo(r.getSchoolLogo());
            if (r.getActive() != null) existing.setActive(r.getActive());
            repo.save(existing);
            return ResponseEntity.ok(existing);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return repo.findById(id).map(existing -> { repo.delete(existing); return ResponseEntity.noContent().<Void>build(); }).orElse(ResponseEntity.notFound().build());
    }
}
