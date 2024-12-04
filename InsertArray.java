import java.util.Arrays;

public class InsertArray {
    public static void main(String[] args) {
        int VOOC[] = { 100, 200, 300, 400 };

        int insertValue = 250;
        int position = 2;

        int[] arr = new int[VOOC.length + 1];

        for (int i = 0; i < position; i++) {
            arr[i] = VOOC[i];
        }

        arr[position] = insertValue;

        for (int i = position; i < VOOC.length; i++) {
            arr[i + 1] = VOOC[i];
        }

        System.out.println("Array after Insertion: " + Arrays.toString(arr));

    }

}
