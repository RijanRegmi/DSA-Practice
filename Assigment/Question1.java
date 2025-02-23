package Assigment;

import java.util.PriorityQueue;

public class Question1 {
    public static int kthSmallestProduct(int[] returns1, int[] returns2, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        for (int i = 0; i < returns1.length; i++) {
            minHeap.offer(new int[] { returns1[i] * returns2[0], i, 0 });
        }

        int product = 0;
        for (int count = 0; count < k; count++) {
            int[] entry = minHeap.poll();
            product = entry[0];
            int i = entry[1], j = entry[2];

            if (j + 1 < returns2.length) {
                minHeap.offer(new int[] { returns1[i] * returns2[j + 1], i, j + 1 });
            }
        }
        return product;
    }

    public static void main(String[] args) {
        int[] returns1 = { 2, 5 };
        int[] returns2 = { 3, 4 };
        System.out.println(kthSmallestProduct(returns1, returns2, 2));

        int[] returns1b = { -4, -2, 0, 3 };
        int[] returns2b = { 2, 4 };
        System.out.println(kthSmallestProduct(returns1b, returns2b, 6));
    }
}
