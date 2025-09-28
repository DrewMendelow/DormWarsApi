package com.dormwars.dormwarsapi.repository;

import com.dormwars.dormwarsapi.model.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
}
