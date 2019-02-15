package com.github.h90871424.hello;

import java.util.ArrayList;
import java.util.List;

class Factors {

    static List<Integer> factors(int n) {
        int[] primes = Sieve.create(n);
        List<Integer> result = new ArrayList<>(primes.length);
        for (int p : primes) {
            while (n % p == 0) {
                result.add(p);
                n = n / p;
            }
        }
        return result;
    }
}
