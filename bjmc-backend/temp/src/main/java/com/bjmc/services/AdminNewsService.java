//// 3. AdminNewsService.java
//package com.bjmc.services;
//
//import com.bjmc.entities.AdminNews;
//import com.bjmc.dao.AdminNewsRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class AdminNewsService {
//
//    @Autowired
//    private AdminNewsRepository newsRepo;
//
//    public List<AdminNews> getAllNews() {
//        return newsRepo.findAll();
//    }
//
//    public AdminNews createNews(AdminNews news) {
//        return newsRepo.save(news);
//    }
//
//    public AdminNews updateNews(Long id, AdminNews updatedNews) {
//        AdminNews existing = newsRepo.findById(id).orElseThrow();
//        existing.setTitle(updatedNews.getTitle());
//        existing.setContent(updatedNews.getContent());
//        existing.setSource(updatedNews.getSource());
//        existing.setPublishDate(updatedNews.getPublishDate());
//        existing.setIsActive(updatedNews.isIsActive());
//        return newsRepo.save(existing);
//    }
//
//    public void deleteNews(Long id) {
//        newsRepo.deleteById(id);
//    }
//}