package com.dormwars.dormwarsapi.repository;

import com.dormwars.dormwarsapi.model.RegisterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterUserRepository extends JpaRepository<RegisterUser, Long> {
}
