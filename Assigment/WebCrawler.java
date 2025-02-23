package Assigment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.*;

public class WebCrawler {
    private final Set<String> visitedUrls = Collections.synchronizedSet(new HashSet<>());
    private final Queue<String> urlQueue = new ConcurrentLinkedQueue<>();
    private final ExecutorService executorService;
    private final int maxDepth;

    public WebCrawler(int threadCount, int maxDepth) {
        this.executorService = Executors.newFixedThreadPool(threadCount);
        this.maxDepth = maxDepth;
    }

    public void startCrawling(String startUrl) {
        urlQueue.add(startUrl);
        List<Future<?>> futures = new ArrayList<>();

        while (!urlQueue.isEmpty()) {
            String url = urlQueue.poll();
            if (url == null || visitedUrls.contains(url)) {
                continue;
            }

            visitedUrls.add(url);
            Callable<Void> task = () -> {
                crawl(url, 0);
                return null;
            };
            futures.add(executorService.submit(task));
        }

        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (Exception e) {
                System.err.println("Error in task execution: " + e.getMessage());
            }
        }

        executorService.shutdown();
    }

    private void crawl(String url, int depth) {
        if (depth > maxDepth) {
            return;
        }
        try {
            System.out.println("Crawling URL: " + url);
            String content = fetchContent(url);

            System.out.println("Fetched content size: " + content.length());

            List<String> links = extractLinks(content);
            for (String link : links) {
                if (!visitedUrls.contains(link)) {
                    urlQueue.add(link);
                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching URL " + url + ": " + e.getMessage());
        }
    }

    private String fetchContent(String urlString) throws Exception {
        StringBuilder content = new StringBuilder();
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }
        return content.toString();
    }

    private List<String> extractLinks(String content) {
        List<String> links = new ArrayList<>();
        String regex = "href=\"(http[s]?://[^\"]+)\"";
        Matcher matcher = Pattern.compile(regex).matcher(content);
        while (matcher.find()) {
            links.add(matcher.group(1));
        }
        return links;
    }

    public static void main(String[] args) {
        String startUrl = "https://example.com";
        int threadCount = 5;
        int maxDepth = 2;

        WebCrawler crawler = new WebCrawler(threadCount, maxDepth);
        crawler.startCrawling(startUrl);
    }
}
