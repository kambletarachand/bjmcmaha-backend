package com.bjmc.controllers;



import com.bjmc.dao.AdminSchemeRepository;
import com.bjmc.entities.AdminScheme;
import com.bjmc.services.AdminSchemeService;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/schemes")
@CrossOrigin(origins = "*")
public class AdminSchemeController {

    private final com.bjmc.services.AdminSchemeService service;

    public AdminSchemeController(AdminSchemeService service) {
        this.service = service;
    }

    @GetMapping
    public List<AdminScheme> getAllSchemes() {
        return service.getAllSchemes();
    }

    @PostMapping
    public AdminSchemeRepository addScheme(@RequestBody AdminScheme scheme) {
        return (AdminSchemeRepository) service.addScheme(scheme);
    }

    @PutMapping("/{id}")
    public AdminScheme updateScheme(@PathVariable Long id, @RequestBody AdminScheme scheme) {
        return service.updateScheme(id, scheme);
    }

    @DeleteMapping("/{id}")
    public void deleteScheme(@PathVariable Long id) {
        service.deleteScheme(id);
    }
}
