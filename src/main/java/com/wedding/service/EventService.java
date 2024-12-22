package com.wedding.service;

import com.wedding.entity.Client;
import com.wedding.entity.Event;
import com.wedding.exception.ClientNotFoundException;
import com.wedding.exception.InvalidEventDateException;
import com.wedding.repository.ClientRepository;
import com.wedding.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ClientRepository clientRepository;

    // Add a new event for a specific client
    public Event createEvent(Long clientId, Event event) throws InvalidEventDateException {
        Optional<Client> clientOptional = clientRepository.findById(clientId);
        if (clientOptional.isEmpty()) {
            throw new ClientNotFoundException("Client not found with ID: " + clientId);
        }

        // Validate event date
        if (event.getDate().isBefore(LocalDate.now())) {
            throw new InvalidEventDateException("Event date cannot be in the past.");
        }

        event.setClient(clientOptional.get());
        return eventRepository.save(event);
    }

    // Retrieve event by ID
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    // List all events with optional filters for upcoming/completed status
    public List<Event> getAllEvents(Optional<String> status) {
        if (status.isPresent()) {
            if (status.get().equalsIgnoreCase("upcoming")) {
                return eventRepository.findByDateAfter(LocalDate.now());
            } else if (status.get().equalsIgnoreCase("completed")) {
                return eventRepository.findByDateBefore(LocalDate.now());
            }
        }
        return eventRepository.findAll();
    }
}

