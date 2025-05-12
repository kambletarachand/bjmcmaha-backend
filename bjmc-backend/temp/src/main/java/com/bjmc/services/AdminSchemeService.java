package com.bjmc.services;





import org.springframework.stereotype.Service;

import com.bjmc.dao.AdminSchemeRepository;
import com.bjmc.entities.AdminScheme;

import java.util.List;

@Service
public class AdminSchemeService {
    private final AdminSchemeRepository repo;

    public AdminSchemeService(AdminSchemeRepository repo) {
        this.repo = repo;
    }

    public List<AdminScheme> getAllSchemes() {
        return repo.findAll();
    }

    public AdminScheme addScheme(AdminScheme scheme) {
        return repo.save(scheme);
    }

    public AdminScheme updateScheme(Long id, AdminScheme updated) {
        return repo.findById(id).map(scheme -> {
            scheme.setTitle(updated.getTitle());
            scheme.setDescription(updated.getDescription());
            scheme.setLink(updated.getLink());
            scheme.setStartDate(updated.getStartDate());
            return repo.save(scheme);
        }).orElseThrow(() -> new RuntimeException("Scheme not found"));
    }

    public void deleteScheme(Long id) {
        repo.deleteById(id);
    }
}
