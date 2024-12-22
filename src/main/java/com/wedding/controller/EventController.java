package com.wedding.controller;

import com.wedding.entity.Event;
import com.wedding.exception.InvalidEventDateException;
import com.wedding.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    // Add an event for a specific client
//    http://localhost:8080/events/client/{clientId}
    @PostMapping("/client/{clientId}")
    public ResponseEntity<Event> createEvent(@PathVariable Long clientId, @RequestBody Event event) throws InvalidEventDateException {
        Event createdEvent = eventService.createEvent(clientId, event);
        return ResponseEntity.ok(createdEvent);
    }

    // Retrieve event by ID
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Optional<Event> event = eventService.getEventById(id);
        return event.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // List all events with optional filters
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents(@RequestParam Optional<String> status) {
        List<Event> events = eventService.getAllEvents(status);
        return ResponseEntity.ok(events);
    }
}

