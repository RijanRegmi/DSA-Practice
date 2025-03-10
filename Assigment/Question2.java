package Assigment;

public class Question2 {
    public static int minRewards(int[] ratings) {
        int n = ratings.length;
        if (n == 0)
            return 0;

        int[] rewards = new int[n];
        java.util.Arrays.fill(rewards, 1);

        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                rewards[i] = rewards[i - 1] + 1;
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                rewards[i] = Math.max(rewards[i], rewards[i + 1] + 1);
            }
        }

        int totalRewards = 0;
        for (int r : rewards) {
            totalRewards += r;
        }

        return totalRewards;
    }

    public static void main(String[] args) {
        int[] ratings1 = { 1, 0, 2 };
        System.out.println(minRewards(ratings1));

        int[] ratings2 = { 1, 2, 2 };
        System.out.println(minRewards(ratings2));
    }

}
