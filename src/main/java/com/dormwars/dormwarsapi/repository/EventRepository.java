package com.dormwars.dormwarsapi.repository;

import com.dormwars.dormwarsapi.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findBySport_SportId(Long schoolId);
}
