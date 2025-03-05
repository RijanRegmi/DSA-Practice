package Assigment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebCrawler {
    private final Queue<String> urlQueue = new LinkedList<>();
    private final Set<String> visitedUrls = new HashSet<>();
    private final ExecutorService executorService;
    private static final int MAX_THREADS = 5;

    public WebCrawler() {
        this.executorService = Executors.newFixedThreadPool(MAX_THREADS);
    }

    public void startCrawling(String startUrl) {
        urlQueue.offer(startUrl);

        while (!urlQueue.isEmpty()) {
            String url = urlQueue.poll();
            if (url != null && !visitedUrls.contains(url)) {
                visitedUrls.add(url);
                executorService.execute(() -> fetchPage(url));
            }
        }

        executorService.shutdown();
    }

    private void fetchPage(String url) {
        try {
            System.out.println("Crawling: " + url);
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    extractUrls(line);
                }
                reader.close();
            }
        } catch (Exception e) {
            System.err.println("Failed to crawl: " + url + " | Error: " + e.getMessage());
        }
    }

    private void extractUrls(String content) {
        if (content.contains("http")) {
            String foundUrl = "http://example.com/newpage";
            if (!visitedUrls.contains(foundUrl)) {
                urlQueue.offer(foundUrl);
            }
        }
    }

    public static void main(String[] args) {
        WebCrawler crawler = new WebCrawler();
        crawler.startCrawling("http://example.com");
    }
}
