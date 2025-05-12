package com.bjmc.services;

import com.bjmc.dao.AdminEventRepository;
import com.bjmc.entities.AdminEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminEventService {
    private final AdminEventRepository repo;

    public AdminEventService(AdminEventRepository repo) {
        this.repo = repo;
    }

    public List<AdminEvent> getAllEvents() {
        return repo.findAll();
    }

    public AdminEvent addEvent(AdminEvent event) {
        return repo.save(event);
    }

    public AdminEvent updateEvent(Long id, AdminEvent updated) {
        return repo.findById(id).map(event -> {
            event.setTitle(updated.getTitle());
            event.setDescription(updated.getDescription());
            event.setLocation(updated.getLocation());
            event.setEventDate(updated.getEventDate());
            return repo.save(event);
        }).orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public void deleteEvent(Long id) {
        repo.deleteById(id);
    }
}
