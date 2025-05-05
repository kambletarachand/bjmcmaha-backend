package com.bjmc.services;

import com.bjmc.dto.NewsItem;
import com.bjmc.entities.News;
import com.bjmc.dao.NewsRepository;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    private NewsItem toNewsItem(News news) {
        NewsItem item = new NewsItem();
        item.setId(news.getId());
        item.setTitle(news.getTitle());
        item.setLink(news.getLink());
        item.setContent(news.getContent());
        item.setAuthor(news.getAuthor());
        item.setImageUrl(news.getImageUrl());
        item.setCreatedAt(news.getCreatedAt());
        return item;
    }

    public List<NewsItem> getAllNews() {
        return newsRepository.findAll()
                .stream()
                .map(this::toNewsItem)
                .collect(Collectors.toList());
    }

    public List<NewsItem> getInternalNews() {
        return newsRepository.findAll()
                .stream()
                .map(this::toNewsItem)
                .collect(Collectors.toList());
    }

    public News createNews(News news) {
        return newsRepository.save(news);
    }

    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }

    public List<NewsItem> fetchExternalNews() {
        List<NewsItem> externalNewsList = new ArrayList<>();
        List<String> feedUrls = List.of(
            "https://news.google.com/rss/search?q=labour+OR+employment+OR+workers&hl=en-IN&gl=IN&ceid=IN:en",
            "https://news.google.com/rss/search?q=कामगार+OR+रोजगार+OR+मजूर+महाराष्ट्र&hl=mr-IN&gl=IN&ceid=IN:mr\r\n",
            "https://labour.gov.in/rss.xml"
//            "https://www.loksatta.com/rss"
//            "https://www.esakal.com/rss-feed",
//            "https://marathi.abplive.com/rss",
//            "https://maharashtratimes.com/rss"
        );

        for (String url : feedUrls) {
            try {
                SyndFeedInput input = new SyndFeedInput();
                SyndFeed feed = input.build(new XmlReader(new URL(url)));

                for (SyndEntry entry : feed.getEntries()) {
                    NewsItem item = new NewsItem();
                    item.setTitle(entry.getTitle());
                    item.setLink(entry.getLink());

                    // Strip HTML from description using Jsoup
                    String cleanSummary = entry.getDescription() != null
                            ? Jsoup.parse(entry.getDescription().getValue()).text()
                            : "";

                    item.setSummary(cleanSummary);
                    item.setPublishedDate(entry.getPublishedDate() != null
                        ? LocalDateTime.ofInstant(entry.getPublishedDate().toInstant(), ZoneId.systemDefault())
                        : LocalDateTime.now());

                    externalNewsList.add(item);
                }

            } catch (Exception e) {
                e.printStackTrace(); // Replace with logger in production
            }
        }

        return externalNewsList;
    }
}




