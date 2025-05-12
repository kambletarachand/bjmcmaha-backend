package com.bjmc.services;
import com.bjmc.dao.NewsRepository;
import com.bjmc.entities.News;
import com.rometools.rome.feed.synd.*;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class IndiaTogetherNewsFetcherService {

    private static final String FEED_URL = "http://indiatogether.org/rss";

    @Autowired
    private NewsRepository newsRepository; // Injecting NewsRepository

    public List<News> fetchNews() {
        List<News> newsList = new ArrayList<>();
        try {
            URL feedSource = new URL(FEED_URL);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedSource));

            for (SyndEntry entry : feed.getEntries()) {
                String description = entry.getDescription() != null ? entry.getDescription().getValue() : "";
                Document doc = Jsoup.parse(description);

                Element img = doc.selectFirst("img");
                String imageUrl = img != null ? img.attr("src") : null;

                Element paragraph = doc.selectFirst("p");
                String summary = paragraph != null ? paragraph.text() : "";

                // Check if this news item already exists in the database by its link
                if (newsRepository.existsByLink(entry.getLink())) {
                    continue; // Skip if the news already exists
                }

                News news = new News();
                news.setTitle(entry.getTitle());
                news.setContent(summary); // You can also use description if necessary
                news.setImageUrl(imageUrl);
                news.setDatePosted(entry.getPublishedDate() != null ? entry.getPublishedDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime() : LocalDateTime.now());
                news.setSourceType("external"); // Mark it as external news
                news.setLink(entry.getLink()); // Save the link to avoid duplicates

                // Save the news to the database
                newsList.add(news);
                newsRepository.save(news);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsList;
    }
}
