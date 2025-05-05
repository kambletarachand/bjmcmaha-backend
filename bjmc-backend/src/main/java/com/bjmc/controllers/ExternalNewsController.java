package com.bjmc.controllers;

import com.bjmc.dto.NewsItem;
import com.bjmc.services.ExternalNewsService;
import com.bjmc.services.IndiaTogetherNewsFetcherService;  // Import the service
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class ExternalNewsController {

    @Autowired
    private ExternalNewsService externalNewsService;

    @Autowired
    private IndiaTogetherNewsFetcherService indiaTogetherNewsFetcherService;  // Autowire the service

    // Endpoint to fetch external news from RSS feed and save it to the database
    @GetMapping("/fetch-external-news")
    public ResponseEntity<String> fetchExternalNewsManually() {
        try {
            // Call the method to fetch and save news from India Together
            indiaTogetherNewsFetcherService.fetchNews();  // Fetch news and store it
            return ResponseEntity.ok("External news fetched and saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to fetch and save external news.");
        }
    }

    // Endpoint to retrieve the external news for the front-end
//    @GetMapping("/external-news")
//    public ResponseEntity<List<NewsItem>> getExternalNews() {
//        try {
//            List<NewsItem> newsItems = externalNewsService.fetchExternalNews();
//
//            // Sort by publishedDate descending
//            newsItems.sort(Comparator.comparing(NewsItem::getPublishedDate, Comparator.nullsLast(Comparator.reverseOrder())));
//
//            return ResponseEntity.ok(newsItems);
//        } catch (Exception e) {
//            e.printStackTrace(); // You can log properly here
//            return ResponseEntity.status(500).body(Collections.emptyList());
//        }
//    }

    @GetMapping("/test")
    public void test() {
        System.out.println("Inside ExternalNewsController");
    }
}
