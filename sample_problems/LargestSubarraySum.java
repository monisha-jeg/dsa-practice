package sample_problems;

/* Find the subarray (containing at least one element) which has the maximum possible sum. */
public class LargestSubarraySum {
    static int largestSubarraySumIterative(int array[]) {
        int cumulativeArray[] = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            cumulativeArray[i] = (i > 0 ? cumulativeArray[i - 1] : 0) + array[i];
        }

        int maxSubarraySum = 0;
        int subarrayStartIndex = -1, subarrayEndIndex = -1;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                if (cumulativeArray[j] - (i > 0 ? cumulativeArray[i - 1] : 0) > maxSubarraySum) {
                    maxSubarraySum = cumulativeArray[j] - (i > 0 ? cumulativeArray[i - 1] : 0);
                    subarrayStartIndex = i;
                    subarrayEndIndex = j;
                }
            }
        }
        System.out.println(
                "Max subarray sum is " + maxSubarraySum + " for subarray [start = " + subarrayStartIndex + ", end = "
                        + subarrayEndIndex + "]");
        return maxSubarraySum;
    }

    public static int largestSubarraySumDp(int array[]) {
        int maxSum = array[0];
        int currentSum = array[0];
        for (int i = 1; i < array.length; i++) {
            if (currentSum < 0)
                currentSum = array[i];
            else
                currentSum += array[i];
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int array[] = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        System.out.println(largestSubarraySumIterative(array) + "\n"); // Output: 6 (subarray [4, -1, 2, 1])
        System.out.println(largestSubarraySumDp(array)); // Output: 6 (subarray [4, -1, 2, 1])
    }
}
