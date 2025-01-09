public class MinMax2 {
    public static void main(String[] args) {
        int[] numbers = { 12, 4, 19, -5, 0, 22, 8 };

        int[] result = findMinMax(numbers);

        System.out.println("Minimum value: " + result[0]);
        System.out.println("Maximum value: " + result[1]);
    }

    public static int[] findMinMax(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }

        int min = numbers[0];
        int max = numbers[0];

        for (int number : numbers) {
            if (number < min) {
                min = number;
            }
            if (number > max) {
                max = number;
            }
        }

        return new int[] { min, max };
    }
}
