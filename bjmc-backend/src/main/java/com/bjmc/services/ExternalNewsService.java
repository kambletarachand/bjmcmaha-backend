package com.bjmc.services;

import com.bjmc.dto.NewsItem;
import com.rometools.rome.feed.synd.*;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.jsoup.Jsoup;


//import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.time.ZoneId;

@Service
public class ExternalNewsService {

    public List<NewsItem> fetchExternalNews() throws Exception {
        System.out.println("Fetching external news...");
        List<NewsItem> allItems = new ArrayList<>();

        // List of RSS feed URLs
        List<String> feedUrls = List.of(
           // "https://economictimes.indiatimes.com/rssfeeds/107112863.cms", // ET Politics
            "https://indiatogether.org/rss/labour" // Labour news from India Together
        );

        // Fetch and process each feed URL
        for (String url : feedUrls) {
            try {
                allItems.addAll(fetchRssFeed(url));
            } catch (Exception e) {
                System.err.println("Failed to fetch or parse feed from: " + url);
                e.printStackTrace(); // More specific error handling
            }
        }

        return allItems;
    }

    // Fetch RSS feed and convert to NewsItem list
    private List<NewsItem> fetchRssFeed(String feedUrl) throws Exception {
        List<NewsItem> items = new ArrayList<>();

        // Fetch the raw content from the URL
        String feedContent = fetchFeedContent(feedUrl);

        // Clean up malformed XML using Jsoup (this may work better than using the XmlReader directly)
        feedContent = Jsoup.parse(feedContent).toString(); // Parses and re-encodes the XML as valid

        // Create an InputStream from the cleaned-up feed content
        InputStream inputStream = new ByteArrayInputStream(feedContent.getBytes(StandardCharsets.UTF_8));

        // Parse the feed
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed;
        try {
            feed = input.build(new XmlReader(inputStream));
        } catch (Exception e) {
            System.err.println("Error parsing feed from URL: " + feedUrl);
            e.printStackTrace();
            return items; // Return empty list if parsing fails
        }

        // Process each entry in the feed
        for (SyndEntry entry : feed.getEntries()) {
            NewsItem item = new NewsItem();
            item.setTitle(entry.getTitle());
            item.setLink(entry.getLink());
            item.setSummary(entry.getDescription() != null ? entry.getDescription().getValue() : "");
            item.setPublishedDate(entry.getPublishedDate() != null
                ? entry.getPublishedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                : null);
            items.add(item);
        }

        return items;
    }


    // Fetch the content of the RSS feed as a string
    private String fetchFeedContent(String feedUrl) throws Exception {
        // Fetch the raw content from the URL
        URL url = new URL(feedUrl);
        return new String(url.openStream().readAllBytes(), StandardCharsets.UTF_8);
    }

    // Clean up malformed RSS feed content
    private String cleanUpRssFeed(String feedContent) {
        // Print the raw feed content for debugging
        System.out.println("Raw RSS Feed Content: ");
        System.out.println(feedContent);

        // Clean up any unclosed <link> tags
        feedContent = feedContent.replaceAll("(<link>[^<]*)(?<!</link>)", "$1</link>");

        // Handle unclosed <channel> tags
        feedContent = feedContent.replaceAll("(<channel[^>]*)(?<!</channel>)", "$1</channel>");

        // Remove DOCTYPE declaration if any
        feedContent = feedContent.replaceAll("<!DOCTYPE [^>]+>", "");

        // Return cleaned-up feed content
        return feedContent;
    }
}
