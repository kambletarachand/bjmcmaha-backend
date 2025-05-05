package com.bjmc.dao;




import com.bjmc.entities.News;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
	 List<News> findBySourceType(String sourceType);
}

