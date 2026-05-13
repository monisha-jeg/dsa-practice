package sample_problems;

/**
 * Split an array of integers into two sections such that the sum of the
 * elements in the two sections are equal. Now discard either section and
 * operate on the other one. Keep doing this until no more splits are possible.
 * What is the max possible number of splits?
 */
public class NikitaGameRecursive {
    static int bestScore(int array[]) {
        int cumulativeArray[] = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            cumulativeArray[i] = (i > 0 ? cumulativeArray[i - 1] : 0) + array[i];
        }
        return bestScoreRecursive(cumulativeArray, 0, array.length - 1);
    }

    static int bestScoreRecursive(int array[], int start, int end) { // end is inclusive.
        if (start >= end) {
            return 0;
        }

        int bestScore = 0;
        for (int i = start; i <= end; i++) {
            // Split at i into sections [start, i - 1] and [i, end].
            int leftSum = (i > 0 ? array[i - 1] : 0) - (start > 0 ? array[start - 1] : 0);
            int rightSum = array[end] - (i > 0 ? array[i - 1] : 0);
            if (leftSum == rightSum) {
                bestScore = 1 + Math.max(bestScore,
                        Math.max(bestScoreRecursive(array, start, i - 1), bestScoreRecursive(array, i, end)));
            }
        }
        return bestScore;
    }

    public static void main(String[] args) {
        System.out.println("Best score " + bestScore(new int[] { 1, 2, 3, 6 }));
    }

}
