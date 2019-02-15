package com.github.h90871424.hello;

import java.util.Arrays;

class Sieve {

    static int[] create(int max) {
        boolean[] prime = new boolean[max + 1];
        Arrays.fill(prime, true);
        long stop = Math.round(Math.sqrt(max));
        for (int n = 2; n <= stop; n++) {
            if (prime[n]) {
                for (int i = 2 * n; i <= max; i += n) {
                    prime[i] = false;
                }
            }
        }
        return toInts(prime);
    }

    private static int[] toInts(boolean[] prime) {
        int numPrimes = 0;
        for (int n = 2; n < prime.length; n++) {
            if (prime[n]) {
                numPrimes++;
            }
        }
        int[] primes = new int[numPrimes];
        int pos = 0;
        for (int n = 2; n < prime.length; n++) {
            if (prime[n]) {
                primes[pos++] = n;
            }
        }
        return primes;
    }
}
