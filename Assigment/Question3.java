package Assigment;

public class Question3 {
    public static int[] findClosestPair(int[] x_coords, int[] y_coords) {
        int n = x_coords.length;
        int minDistance = Integer.MAX_VALUE;
        int minI = -1, minJ = -1;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int distance = Math.abs(x_coords[i] - x_coords[j]) + Math.abs(y_coords[i] - y_coords[j]);

                if (distance < minDistance || (distance == minDistance && (i < minI || (i == minI && j < minJ)))) {
                    minDistance = distance;
                    minI = i;
                    minJ = j;
                }
            }
        }

        return new int[] { minI, minJ };
    }

    public static void main(String[] args) {
        int[] x_coords = { 1, 2, 3, 2, 4 };
        int[] y_coords = { 2, 3, 1, 2, 3 };
        int[] result = findClosestPair(x_coords, y_coords);
        System.out.println("[" + result[0] + ", " + result[1] + "]");
    }

}
