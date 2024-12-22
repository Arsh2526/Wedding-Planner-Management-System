package com.wedding.repository;

import com.wedding.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByDateAfter(LocalDate now);

    List<Event> findByDateBefore(LocalDate now);
}