// 2. AdminNewsRepository.java
package com.bjmc.dao;

import com.bjmc.entities.AdminNews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminNewsRepository extends JpaRepository<AdminNews, Long> {
}