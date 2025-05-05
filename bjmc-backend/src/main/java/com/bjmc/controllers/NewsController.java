package com.bjmc.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bjmc.entities.News;
import com.bjmc.dto.NewsItem;
import com.bjmc.services.NewsService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    // All news
    @GetMapping("/news")
    public List<NewsItem> getAllNews() {
        return newsService.getAllNews();
    }

    // Admin-posted news only
    @GetMapping("/internal-news")
    public List<NewsItem> getInternalNews() {
        return newsService.getInternalNews();
    }

    // Fetch from RSS feeds
    @GetMapping("/external-news")
    public List<NewsItem> getExternalNews() {
        return newsService.fetchExternalNews();
    }

    @PostMapping("/admin/news")
    public News createNews(@RequestBody News news) {
        return newsService.createNews(news);
    }

    @DeleteMapping("/admin/news/{id}")
    public void deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
    }
}
