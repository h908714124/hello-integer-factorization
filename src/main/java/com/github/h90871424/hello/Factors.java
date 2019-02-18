package com.github.h90871424.hello;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Factors {

    private final int LIMIT = (Integer.MAX_VALUE - 100) / 5;

    private final List<BigInteger> primes = Arrays.stream(Sieve.create(LIMIT))
            .mapToObj(BigInteger::valueOf)
            .collect(Collectors.toList());

    private final BigInteger maxPrime = primes.get(primes.size() - 1);

    List<BigInteger> factors(int n) {
        return factors(BigInteger.valueOf(n));
    }

    List<BigInteger> factors(BigInteger n) {
        List<BigInteger> result = new ArrayList<>();
        for (BigInteger p : primes) {
            while (n.remainder(p).equals(BigInteger.ZERO)) {
                result.add(p);
                n = n.divide(p);
            }
            if (n.compareTo(p) < 0) {
                break;
            }
        }
        if (n.compareTo(maxPrime) > 0) {
            if (n.isProbablePrime(14)) {
                result.add(n);
            } else {
                throw new IllegalStateException("Boese: " + n);
            }
        }
        return result;
    }
}
