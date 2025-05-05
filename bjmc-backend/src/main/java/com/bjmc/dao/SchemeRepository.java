package com.bjmc.dao;



import com.bjmc.entities.Scheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchemeRepository extends JpaRepository<Scheme, Long> {

  //  List<Scheme> findByCategoryContainingIgnoreCase(String category);
    List<Scheme> findByTitleContainingIgnoreCase(String title);
}
