public class MinMax1 {
    public static void main(String[] args) {
        int[] array = { 21, 23, 13, 42, 2, 4, 32 };
        int max = 0;
        int min = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            } else if (array[i] < min) {
                min = array[i];
            }
        }
        System.out.println("This is min" + min);
        System.out.println("This is max" + max);
    }
}
