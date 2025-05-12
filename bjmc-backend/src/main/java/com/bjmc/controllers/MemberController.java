package com.bjmc.controllers;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bjmc.dao.*;
import com.bjmc.entities.Member;
import java.util.Map;


@RestController
@RequestMapping("/api/members")
@CrossOrigin("http://localhost:3000") // Or limit to your frontend domain
public class MemberController {

    @Autowired
    private MemberRepository memberRepo;

    @PostMapping
    public ResponseEntity<?> createMember(@RequestBody Member member) {
        if (memberRepo.existsByPhone(member.getPhone())) {
            return ResponseEntity.badRequest().body(Map.of("message", "Phone already registered"));
        }

        Member saved = memberRepo.save(member);
        return ResponseEntity.ok(Map.of("message", "Member saved", "id", saved.getId()));
    }
}
