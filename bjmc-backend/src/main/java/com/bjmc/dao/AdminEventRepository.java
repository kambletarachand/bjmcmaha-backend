package com.bjmc.dao;

import com.bjmc.entities.AdminEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminEventRepository extends JpaRepository<AdminEvent, Long> {
}
