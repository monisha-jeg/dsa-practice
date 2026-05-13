package sample_problems;

/**
 * Check if there is a subset of the given array whose sum is equal to the
 * given non-zero sum.
 */
public class SubsetSum {
    public static boolean hasSubsetSumRecursive(int array[], int sum) {
        return hasSubsetSumRecursive(array, sum, 0);
    }

    public static boolean hasSubsetSumRecursive(int array[], int sum, int index) {
        if (sum == 0) {
            return true;
        }
        if (index >= array.length) {
            return false;
        }
        return hasSubsetSumRecursive(array, sum - array[index], index + 1)
                || hasSubsetSumRecursive(array, sum, index + 1);
    }

    public static boolean hasSubsetSumDp(int array[], int sum) {
        boolean hasSum[][] = new boolean[array.length + 1][sum + 1];
        for (int i = 0; i < array.length + 1; i++) {
            for (int s = 0; s <= sum; s++) {
                if (s == 0) {
                    hasSum[i][s] = true;
                } else if (i == 0) {
                    hasSum[i][s] = false;
                } else if (array[i - 1] <= s) {
                    hasSum[i][s] = hasSum[i - 1][s] || hasSum[i - 1][s - array[i - 1]];
                } else {
                    hasSum[i][s] = hasSum[i - 1][s];
                }
            }
        }
        printSelectedItems(hasSum, array, sum);
        return hasSum[array.length][sum];
    }

    static void printSelectedItems(boolean hasSum[][], int array[], int sum) {

    }

    public static boolean hasSubsetSumDp1DStorage(int array[], int sum) {
        boolean hasSum[] = new boolean[sum + 1];
        hasSum[0] = true;
        for (int i = 1; i < array.length + 1; i++) {
            // hasSum[i] is false by default for i = 0, which is what we want.
            for (int s = sum; s >= array[i - 1]; s--) {
                hasSum[s] = hasSum[s] || hasSum[s - array[i - 1]];
            }
        }
        return hasSum[sum];
    }

    public static void main(String[] args) {
        int array[] = { 3, 34, 4, 12, 5, 2 };
        int sum = 9;
        System.out.println(hasSubsetSumRecursive(array, sum) + "\n"); // Output: true (subset [4, 5])
        System.out.println(hasSubsetSumDp(array, sum) + "\n"); // Output: true (subset [4, 5])
        System.out.println(hasSubsetSumDp1DStorage(array, sum) + "\n"); // Output: true (subset [4, 5])
    }
}
