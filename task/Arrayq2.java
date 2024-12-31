import java.util.HashMap;
import java.util.Map;

public class Arrayq2 {
    public static void main(String[] args) {
        int[] array = { 10, 12, 8, 5, 13, 3, 19, 11, 5, 8, 10, 13 };

        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int num : array) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        System.out.println("Element Frequencies:");
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            System.out.println("Element: " + entry.getKey() + ", Frequency: " + entry.getValue());
        }
    }
}
