package sample_problems;

import java.util.*;

/** Sieve of Eratosthenes */
public class SieveOfEratosthenes {
    public static void sieveOfEratosthenes(int n, List<Integer> v) {
        if (n < 2)
            return;

        // 1. Create the array and fill it with true
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);

        // 2. 0 and 1 are not prime numbers
        isPrime[0] = false;
        isPrime[1] = false;

        // 3. Sieve logic
        for (int p = 2; p * p <= n; p++) {
            // If isPrime[p] is not changed, then it is a prime
            if (isPrime[p]) {
                // Update all multiples of p starting from p*p
                for (int i = p * p; i <= n; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        // 4. Collect results (Outside the nested loop)
        for (int p = 2; p <= n; p++) {
            if (isPrime[p]) {
                v.add(p);
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> primes = new ArrayList<>();
        sieveOfEratosthenes(30, primes);
        System.out.println(primes);
    }
}
