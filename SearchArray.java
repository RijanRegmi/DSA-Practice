public class SearchArray {
    public static void main(String[] args) {
        int VOOC[] = { 100, 200, 300, 400 };

        int arr = 300;
        boolean arr1 = true;

        for (int i = 0; i < VOOC.length; i++) {
            if (VOOC[i] == arr) {
                System.out.println("Element " + arr + " found at index " + i);
                arr1 = true;
                break;
            }
        }

    }

}
