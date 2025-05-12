package com.bjmc.controllers;

import com.bjmc.entities.AdminEvent;
import com.bjmc.services.AdminEventService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin/events")
@CrossOrigin("http://localhost:3000")
public class AdminEventController {

    private final AdminEventService service;

    public AdminEventController(AdminEventService service) {
        this.service = service;
    }

    @GetMapping
    public List<AdminEvent> getAllEvents() {
    	System.out.println("getting all events");
        return service.getAllEvents();
    }

    @PostMapping
    public AdminEvent addEvent(@RequestBody AdminEvent event) {
    	System.out.println("Posting new Event");
        return service.addEvent(event);
    }

    @PutMapping("/{id}")
    public AdminEvent updateEvent(@PathVariable Long id, @RequestBody AdminEvent event) {
        return service.updateEvent(id, event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        service.deleteEvent(id);
    }

    

    @PostMapping(consumes = {"multipart/form-data"})
    public AdminEvent addEventWithImages(@RequestPart("event") AdminEvent event,
                                         @RequestPart("images") MultipartFile[] images) throws IOException {
    	System.out.println("Uploading IMAGE");
        List<String> savedPaths = new ArrayList<>();
        for (MultipartFile file : images) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path path = Paths.get("uploads/events/" + fileName);
            Files.createDirectories(path.getParent());
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            savedPaths.add("/uploads/events/" + fileName);
        }
        event.setImagePaths(savedPaths);
        return service.addEvent(event);
    }



}
