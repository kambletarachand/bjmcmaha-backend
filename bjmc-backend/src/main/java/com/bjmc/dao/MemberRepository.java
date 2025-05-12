package com.bjmc.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bjmc.entities.Member;


public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByPhone(String phone);
}
