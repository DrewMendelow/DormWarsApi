package com.dormwars.dormwarsapi.controller;

import com.dormwars.dormwarsapi.model.User;
import com.dormwars.dormwarsapi.model.School;
import com.dormwars.dormwarsapi.repository.UserRepository;
import com.dormwars.dormwarsapi.repository.SchoolRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public User create(@RequestBody User user) {
        if (user.getSchool() != null && user.getSchool().getSchoolId() != null) {
            School s = schoolRepo.findById(user.getSchool().getSchoolId()).orElse(null);
            user.setSchool(s);
        }
        return repo.save(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User in) {
        return repo.findById(id).map(existing -> {
            existing.setFirstName(in.getFirstName());
            existing.setLastName(in.getLastName());
            existing.setEmail(in.getEmail());
            existing.setPhoneNumber(in.getPhoneNumber());
            existing.setUserType(in.getUserType());
            if (in.getSchool() != null && in.getSchool().getSchoolId() != null) {
                School s = schoolRepo.findById(in.getSchool().getSchoolId()).orElse(null);
                existing.setSchool(s);
            } else {
                existing.setSchool(null);
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
