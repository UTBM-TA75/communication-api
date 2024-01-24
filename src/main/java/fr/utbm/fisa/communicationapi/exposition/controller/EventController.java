package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Event;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EventController {
    private final EventRepository eventRepository;

    /**
     * Gets all the events
     *
     * @return the list of events
     */
    @GetMapping("/events")
    public ResponseEntity<Iterable<Event>> getAllEvents() {
        return ResponseEntity.ok(eventRepository.findAll());
    }

    /**
     * Get an event
     *
     * @param id the event's id
     * @return the event
     */
    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable int id) {
        Event event = eventRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No event found with id " + id));

        return ResponseEntity.ok(event);
    }

    /**
     * Creates an event
     *
     * @param data the event's data
     * @return the created event
     */
    @PostMapping("/events")
    public ResponseEntity<Event> createEvent(@RequestBody Event data) {
        //TODO : add createdBy field when JWT will be up
        Event event = new Event();
        event.setTitle(data.getTitle());
        event.setDescription(data.getDescription());
        event.setBeginning(data.getBeginning());
        event.setEnd(data.getEnd());
        event.setImageUrl(data.getImageUrl());
        event.setLocation(data.getLocation());
        event.setClassroom(data.getClassroom());

        eventRepository.save(event);

        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    /**
     * Updates an event
     *
     * @param id   the event's id
     * @param data the event's updated data
     * @return the updated event
     */
    @PutMapping("/events/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable int id, @RequestBody Event data) {
        Event event = eventRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No event found with id " + id));

        event.setTitle(data.getTitle());
        event.setDescription(data.getDescription());
        event.setBeginning(data.getBeginning());
        event.setEnd(data.getEnd());
        event.setImageUrl(data.getImageUrl());
        event.setLocation(data.getLocation());
        event.setClassroom(data.getClassroom());

        eventRepository.save(event);

        return ResponseEntity.ok(event);
    }

    /**
     * Deletes an event
     *
     * @param id the event's id
     */
    @DeleteMapping("/events/{id}")
    public void deleteEvent(@PathVariable int id) {
        Event event = eventRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No event found with id " + id));

        eventRepository.deleteById(event.getId());
    }
}
