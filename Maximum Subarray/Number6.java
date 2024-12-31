public class Number6 {
    public static int maxSubArray(int[] nums) {
        int max = nums[1];
        int sum = 0;
        int start = 0;
        int end = 0;
        int temstart = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > max) {
                max = sum;
                start = temstart;
                end = i;
            }
            if (sum < 0) {
                temstart = i + 1;
                sum = 0;
            }
        }

    }

    public static void main(String[] args) {
        int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        int result = maxSubArray(nums);
        System.out.println("Maximum Subarray Sum: " + result);
    }
}