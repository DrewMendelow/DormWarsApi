package com.dormwars.dormwarsapi.repository;

import com.dormwars.dormwarsapi.model.EventTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventTeamRepository extends JpaRepository<EventTeam, Long> {
}
