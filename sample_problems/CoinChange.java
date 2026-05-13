package sample_problems;

/**
 * Find number of ways to make change for a given amount using given coin
 * denominations, where each coin can be used infinite times. If each coin can
 * only be used once, this is essentially the knspsack problem but we are
 * computing the number of ways to make change instead of the maximum value.
 */
public class CoinChange {
    public static int countWaysRecursive(int coins[], int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 0;
        }
        int ways = 0;
        for (int coin : coins) {
            if (coin <= n) {
                ways += countWaysRecursive(coins, n - coin);
            }
        }
        return ways;
    }

    public static int countWaysDp(int coins[], int n) {
        int ways[] = new int[n + 1];
        ways[0] = 1;
        for (int i = 1; i <= n; i++) {
            ways[i] = 0;
            for (int coin : coins) {
                if (coin <= i) {
                    ways[i] += ways[i - coin];
                }
            }
        }
        return ways[n];
    }

    public static void main(String[] args) {
        int coins[] = { 1, 2, 3, 4, 5 };
        int n = 5;
        System.out.println(countWaysRecursive(coins, n));
        System.out.println(countWaysDp(coins, n));
    }
}
