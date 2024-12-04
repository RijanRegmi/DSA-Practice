import java.util.Arrays;

public class DeleteArray {
    public static void main(String[] args) {
        int[] VOOC = { 100, 200, 300, 400 };

        int deleteValue = 300;

        int deleteIndex = -1;
        for (int i = 0; i < VOOC.length; i++) {
            if (VOOC[i] == deleteValue) {
                deleteIndex = i;
                break;
            }
        }

        if (deleteIndex == -1) {
            System.out.println("Element " + deleteValue + " not found in the array.");
        } else {

            int[] newArray = new int[VOOC.length - 1];

            for (int i = 0; i < deleteIndex; i++) {
                newArray[i] = VOOC[i];
            }

            for (int i = deleteIndex + 1; i < VOOC.length; i++) {
                newArray[i - 1] = VOOC[i];
            }

            System.out.println("Array after Deletion of " + deleteValue + ": " + Arrays.toString(newArray));
        }
    }

}