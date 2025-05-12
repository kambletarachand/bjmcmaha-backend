//// 4. AdminNewsController.java
//package com.bjmc.controllers;
//
//import com.bjmc.entities.AdminNews;
//import com.bjmc.services.AdminNewsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/admin/news")
//@CrossOrigin("http://localhost:3000")
//public class AdminNewsController {
//
//    @Autowired
//    private AdminNewsService newsService;
//
//    @GetMapping("/admin-posted")
//    public List<AdminNews> getAllNews() {
//        return newsService.getAllNews();
//    }
//
//    @PostMapping
//    public AdminNews createNews(@RequestBody AdminNews news) {
//        return newsService.createNews(news);
//    }
//
//    @PutMapping("/{id}")
//    public AdminNews updateNews(@PathVariable Long id, @RequestBody AdminNews news) {
//        return newsService.updateNews(id, news);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteNews(@PathVariable Long id) {
//        newsService.deleteNews(id);
//    }
//}