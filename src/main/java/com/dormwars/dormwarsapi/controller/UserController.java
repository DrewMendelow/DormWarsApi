package com.dormwars.dormwarsapi.controller;

import com.dormwars.dormwarsapi.model.User;
import com.dormwars.dormwarsapi.model.UserRequest;
import com.dormwars.dormwarsapi.model.School;
import com.dormwars.dormwarsapi.repository.UserRepository;
import com.dormwars.dormwarsapi.repository.SchoolRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository repo;
    private final SchoolRepository schoolRepo;

    public UserController(UserRepository repo, SchoolRepository schoolRepo) { this.repo = repo; this.schoolRepo = schoolRepo; }

    @GetMapping
    public List<User> list() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserRequest r) {
        User user = new User();
        user.setFirstName(r.getFirstName());
        user.setLastName(r.getLastName());
        user.setEmail(r.getEmail());
        user.setPhoneNumber(r.getPhoneNumber());
        user.setUserType(r.getUserType());

        if (r.getSchoolId() != null) {
            Optional<School> s = schoolRepo.findById(r.getSchoolId());
            if (s.isEmpty()) return ResponseEntity.status(404).body("school not found");
            user.setSchool(s.get());
        } else {
            user.setSchool(null);
        }

        User saved = repo.save(user);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserRequest r) {
        return repo.findById(id).map(existing -> {
            if (r.getFirstName() != null) existing.setFirstName(r.getFirstName());
            if (r.getLastName() != null) existing.setLastName(r.getLastName());
            if (r.getEmail() != null) existing.setEmail(r.getEmail());
            if (r.getPhoneNumber() != null) existing.setPhoneNumber(r.getPhoneNumber());
            if (r.getUserType() != null) existing.setUserType(r.getUserType());

            if (r.getSchoolId() != null) {
                Optional<School> s = schoolRepo.findById(r.getSchoolId());
                if (s.isEmpty()) return ResponseEntity.status(404).body("school not found");
                existing.setSchool(s.get());
            } else if (r.getSchoolId() == null) {
                // leave as-is when not provided
            }

            repo.save(existing);
            return ResponseEntity.ok(existing);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return repo.findById(id).map(existing -> {
            repo.delete(existing);
            return ResponseEntity.noContent().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
