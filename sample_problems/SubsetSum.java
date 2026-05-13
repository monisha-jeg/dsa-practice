package sample_problems;

import java.util.Arrays;
import java.util.Stack;

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
        if (index >= array.length || sum < 0) {
            return false;
        }
        return hasSubsetSumRecursive(array, sum - array[index], index + 1)
                || hasSubsetSumRecursive(array, sum, index + 1);
    }

    static void initialize(Boolean array[][]) {
        for (Boolean[] row: array)
            Arrays.fill(row, null);
    }

    public static boolean hasSubsetSumRecursiveDp(int array[], int sum) {
        Boolean hasSum[][] = new Boolean[array.length + 1][sum + 1];
        initialize(hasSum);
        boolean included[] = new boolean[array.length];
        boolean hasSumFinal = hasSubsetSumRecursiveDp(array, sum, array.length, hasSum, included);
        for (int i = 0; i < included.length; i++) {
            if (included[i])
                System.out.println("Selected " + array[i]);
        }
        return hasSumFinal;
    }

    public static boolean hasSubsetSumRecursiveDp(int array[], int sum, int n, Boolean hasSum[][], boolean included[]) {
        if (sum == 0) {
            hasSum[n][sum] = true;
        } else if (n == 0 || sum < 0) {
            hasSum[n][sum] = false;
        } else if (hasSum[n][sum] != null) {
            // We already have the value.
        } else if (array[n - 1] <= sum) {
            boolean includingElement = hasSubsetSumRecursiveDp(array, sum - array[n - 1], n - 1, hasSum, included);
            boolean excludingElement = hasSubsetSumRecursiveDp(array, sum, n - 1, hasSum, included);
            if (includingElement) {
                 // Only works if there is only one solution. Otherwise, this will be the union of elements in all the solutions.
                included[n - 1] = true;
            }
            hasSum[n][sum] = includingElement || excludingElement;
        } else {
            hasSum[n][sum] = hasSubsetSumRecursiveDp(array, sum, n - 1, hasSum, included);
        }
        return hasSum[n][sum];
    }

    public static boolean hasSubsetSumIterativeDp(int array[], int sum) {
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
        return hasSum[array.length][sum];
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
        int array[] = { 3, 60, 4, 12, 5, 21 };
        int sum = 9;
        System.out.println(hasSubsetSumRecursive(array, sum) + "\n"); // Output: true (subset [4, 5])
        System.out.println(hasSubsetSumRecursiveDp(array, sum) + "\n"); // Output: true (subset [4, 5])
        System.out.println(hasSubsetSumIterativeDp(array, sum) + "\n"); // Output: true (subset [4, 5])
        System.out.println(hasSubsetSumDp1DStorage(array, sum) + "\n"); // Output: true (subset [4, 5])
    }
}
