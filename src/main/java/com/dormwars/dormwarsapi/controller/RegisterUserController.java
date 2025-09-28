package com.dormwars.dormwarsapi.controller;

import com.dormwars.dormwarsapi.model.RegisterUser;
import com.dormwars.dormwarsapi.repository.RegisterUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
public class RegisterUserController {

    private final RegisterUserRepository repo;

    public RegisterUserController(RegisterUserRepository repo) { this.repo = repo; }

    @GetMapping
    public List<RegisterUser> list() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<RegisterUser> get(@PathVariable Long id) { return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }

    @PostMapping
    public RegisterUser create(@RequestBody RegisterUser r) { return repo.save(r); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { return repo.findById(id).map(existing -> { repo.delete(existing); return ResponseEntity.noContent().<Void>build(); }).orElse(ResponseEntity.notFound().build()); }
}
