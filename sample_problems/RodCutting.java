package sample_problems;

/**
 * Determine the maximum amount obtained by cutting the rod
 * into pieces and selling the pieces, given the prices of the pieces and the
 * length of the rod.
 */
class RodCutting {
    public static int bestPriceRecursive(int prices[], int n) {
        int optimalCuts[] = new int[n + 1];
        int bestPrice = bestPriceRecursive(prices, n, optimalCuts);
        printOptimalCuts(optimalCuts, n);
        return bestPrice;
    }

    public static int bestPriceRecursive(int prices[], int n, int optimalCuts[]) {
        if (n <= 0) {
            return 0;
        }
        int maxPrice = prices[n];
        optimalCuts[n] = n;
        for (int i = 1; i < n; i++) {
            // Cut at index i -> pieces of length i and n - i.
            int price = prices[i] + bestPriceRecursive(prices, n - i, optimalCuts);
            if (price > maxPrice) {
                maxPrice = price;
                optimalCuts[n] = i; // Store the cut that gives the best price for length n.
            }
        }
        return maxPrice;
    }

    public static int bestPriceRecursiveDp(int prices[], int n) {
        int bestPrice[] = new int[n + 1];
        int optimalCuts[] = new int[n + 1];
        int bestPriceFinal = bestPriceRecursiveDp(prices, n, optimalCuts, bestPrice);
        printOptimalCuts(optimalCuts, n);
        return bestPriceFinal;
    }

    public static int bestPriceRecursiveDp(int prices[], int n, int optimalCuts[], int bestPrice[]) {
        if (n <= 0) {
            bestPrice[n] = 0;
        } else if (bestPrice[n] != 0) {
            // Already calculated.
        } else {
            int maxPrice = prices[n];
            optimalCuts[n] = n;
            for (int i = 1; i < n; i++) {
                // Cut at index i -> pieces of length i and n - i.
                int price = prices[i] + bestPriceRecursive(prices, n - i, optimalCuts);
                if (price > maxPrice) {
                    maxPrice = price;
                    optimalCuts[n] = i; // Store the cut that gives the best price for length n.
                }
            }
            bestPrice[n] = maxPrice;
        }
        return bestPrice[n];
    }

    public static int bestPriceDp(int prices[], int n) {
        int bestPrice[] = new int[n + 1];
        int optimalCuts[] = new int[n + 1];
        bestPrice[0] = 0;
        for (int i = 1; i <= n; i++) {
            bestPrice[i] = prices[i]; // No cutting at all.
            optimalCuts[i] = i; // Initialize optimal cut for each length.
            for (int j = 1; j < i; j++) {
                if (prices[j] + bestPrice[i - j] > bestPrice[i]) {
                    bestPrice[i] = prices[j] + bestPrice[i - j];
                    optimalCuts[i] = j;
                }
            }
        }
        printOptimalCuts(optimalCuts, n);
        return bestPrice[n];
    }

    private static void printOptimalCuts(int optimalCuts[], int n) {
        System.out.print("Optimal cuts for rod of length " + n + ": ");
        while (n > 0) {
            System.out.print(optimalCuts[n] + " ");
            n = n - optimalCuts[n];
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int prices[] = { 0, 1, 5, 8, 9, 10, 17, 17, 20 };
        int n = 8;
        System.out.println(bestPriceRecursive(prices, n) + "\n");
        System.out.println(bestPriceRecursiveDp(prices, n) + "\n");
        System.out.println(bestPriceDp(prices, n));
    }
}
