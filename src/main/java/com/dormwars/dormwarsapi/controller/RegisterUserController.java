package com.dormwars.dormwarsapi.controller;

import com.dormwars.dormwarsapi.model.RegisterUser;
import com.dormwars.dormwarsapi.model.RegisterUserRequest;
import com.dormwars.dormwarsapi.model.EventTeam;
import com.dormwars.dormwarsapi.model.User;
import com.dormwars.dormwarsapi.repository.RegisterUserRepository;
import com.dormwars.dormwarsapi.repository.EventTeamRepository;
import com.dormwars.dormwarsapi.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/registrations")
public class RegisterUserController {

    private final RegisterUserRepository repo;
    private final EventTeamRepository eventTeamRepo;
    private final UserRepository userRepo;

    public RegisterUserController(RegisterUserRepository repo, EventTeamRepository eventTeamRepo, UserRepository userRepo) { this.repo = repo; this.eventTeamRepo = eventTeamRepo; this.userRepo = userRepo; }

    @GetMapping
    public List<RegisterUser> list() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<RegisterUser> get(@PathVariable Long id) { return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RegisterUserRequest r) {
        if (r.getEventTeamId() == null || r.getUserId() == null) return ResponseEntity.badRequest().body("eventTeamId and userId are required");

        Optional<EventTeam> et = eventTeamRepo.findById(r.getEventTeamId());
        if (et.isEmpty()) return ResponseEntity.status(404).body("event team not found");

        Optional<User> u = userRepo.findById(r.getUserId());
        if (u.isEmpty()) return ResponseEntity.status(404).body("user not found");

        RegisterUser ru = new RegisterUser();
        ru.setEventTeam(et.get());
        ru.setUser(u.get());

        RegisterUser saved = repo.save(ru);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { return repo.findById(id).map(existing -> { repo.delete(existing); return ResponseEntity.noContent().<Void>build(); }).orElse(ResponseEntity.notFound().build()); }
}
