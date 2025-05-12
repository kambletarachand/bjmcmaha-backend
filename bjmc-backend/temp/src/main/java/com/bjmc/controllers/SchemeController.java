package com.bjmc.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bjmc.entities.Scheme;
import com.bjmc.services.SchemeService;

import java.util.List;

@RestController
@RequestMapping("/api/schemes")
@CrossOrigin(origins = "http://localhost:3000")
public class SchemeController {

    @Autowired
    private SchemeService schemeService;

    @GetMapping("/labour")
    public List<Scheme> getLabourSchemes() {
        return schemeService.getLabourSchemes();
    }

    @GetMapping("/pradhanmantri-yojna")
    public List<Scheme> getPradhanMantriYojanas() {
        return schemeService.getPradhanMantriYojanas();
    }

    @GetMapping("/govt-job-cards")
    public List<Scheme> getGovtJobCards() {
        return schemeService.getGovtJobCards();
    }
}
