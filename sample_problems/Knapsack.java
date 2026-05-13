package sample_problems;

/**
 * Given weights and values of n items, put these items in a knapsack of
 * capacity W to get the maximum total value in the knapsack.
 * If you can each item multiple times, this is essentially the coin change
 * problem, but we are computing the maximum value instead of the number of ways
 * to make change.
 */
public class Knapsack {
    static int bestValueRecursive(int values[], int weights[], int W) {
        boolean valuesChosen[] = new boolean[values.length];
        int bestValue = bestValueRecursive(values, weights, W, 0, valuesChosen);
        for (int i = 0; i < valuesChosen.length; i++) {
            if (valuesChosen[i]) {
                System.out.println("Selected item " + i);
            }
        }
        return bestValue;
    }

    static int bestValueRecursive(int values[], int weights[], int W, int index, boolean valuesChosen[]) {
        if (W <= 0 || index >= values.length) {
            return 0;
        }
        if (weights[index] > W) {
            return bestValueRecursive(values, weights, W, index + 1, valuesChosen);
        }
        int valueWithItem = values[index]
                + bestValueRecursive(values, weights, W - weights[index], index + 1, valuesChosen);
        int valueWithoutItem = bestValueRecursive(values, weights, W, index + 1, valuesChosen);
        if (valueWithItem > valueWithoutItem) {
            valuesChosen[index] = true;
            return valueWithItem;
        } else {
            return valueWithoutItem;
        }
    }

    static void printSelecteditems(int bestValue[][], int weights[], int W) {
        for (int i = weights.length, w = W; i > 0 && w >= 0; i--) {
            if (bestValue[i][w] != bestValue[i - 1][w]) {
                System.out.println("Selected item " + (i - 1));
                w = w - weights[i - 1];
            }
        }
    }

    static int bestValueDp(int values[], int weights[], int W) {
        int bestValue[][] = new int[values.length + 1][W + 1];
        for (int i = 0; i < values.length + 1; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0) {
                    bestValue[i][w] = 0;
                } else if (weights[i - 1] > w) {
                    bestValue[i][w] = bestValue[i - 1][w];
                } else {
                    bestValue[i][w] = Math.max(values[i - 1] + bestValue[i - 1][w - weights[i - 1]],
                            bestValue[i - 1][w]);
                }
            }
        }
        printSelecteditems(bestValue, weights, W);
        return bestValue[values.length][W];
    }

    static int bestValueDp1DStorage(int values[], int weights[], int W) {
        int bestValue[] = new int[W + 1];
        for (int i = 1; i < values.length + 1; i++) {
            // hasSum[i] is false by default for i = 0, which is what we want.
            for (int w = W; w >= weights[i - 1]; w--) {
                bestValue[w] = Math.max(values[i - 1] + bestValue[w - weights[i - 1]],
                        bestValue[w]);
            }
        }
        return bestValue[W];
    }

    public static void main(String[] args) {
        int[] values = { 60, 100, 120 };
        int[] weights = { 10, 20, 30 };
        int W = 50;
        System.out.println(bestValueRecursive(values, weights, W) + "\n");
        System.out.println(bestValueDp(values, weights, W) + "\n");
        System.out.println(bestValueDp1DStorage(values, weights, W) + "\n");
    }
}
