package Assigment;

import java.util.*;
import java.util.regex.*;

public class TrendingHashtags {
    public static void main(String[] args) {
        String[][] tweets = {
                { "135", "13", "Enjoying a great start to the day. #HappyDay #MorningVibes", "2024-02-02" },
                { "136", "14", "Another #HappyDay with good vibes! #FeelGood", "2024-02-05" },
                { "141", "15", "Productivity peak! #WorkLife #Productive", "2024-02-10" },
                { "138", "16", "Exploring new frontiers. #TechLife #Innovation", "2024-02-15" },
                { "136", "17", "Gratitude for today's moments. #HappyDay #Thankful", "2024-02-22" },
                { "142", "18", "Innovation drives us. #TechLife #FutureTech", "2024-02-27" },
                { "144", "19", "Connecting with nature's serenity. #Nature #Peaceful", "2024-02-28" }
        };

        Map<String, Integer> hashtagCount = new HashMap<>();
        Pattern hashtagPattern = Pattern.compile("#\\w+");

        for (String[] tweet : tweets) {
            String tweetText = tweet[2];
            String date = tweet[3];

            if (date.startsWith("2024-02")) {
                Matcher matcher = hashtagPattern.matcher(tweetText);
                while (matcher.find()) {
                    String hashtag = matcher.group();
                    hashtagCount.put(hashtag, hashtagCount.getOrDefault(hashtag, 0) + 1);
                }
            }
        }

        List<Map.Entry<String, Integer>> sortedHashtags = new ArrayList<>(hashtagCount.entrySet());
        sortedHashtags.sort((a, b) -> {
            if (!b.getValue().equals(a.getValue())) {
                return b.getValue() - a.getValue();
            }
            return a.getKey().compareTo(b.getKey());
        });

        System.out.printf("%-12s | %-5s%n", "Hashtag", "Count");
        System.out.println("---------------------");

        int rank = 0;
        for (Map.Entry<String, Integer> entry : sortedHashtags) {
            System.out.printf("%-12s | %-5d%n", entry.getKey(), entry.getValue());
            if (++rank == 3)
                break;
        }
    }
}
