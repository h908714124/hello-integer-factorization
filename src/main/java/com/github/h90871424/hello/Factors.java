package com.github.h90871424.hello;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Factors {

    private final int LIMIT = (Integer.MAX_VALUE - 100) / 5;

    private final List<BigInteger> primes = Sieve.primeList(LIMIT);

    private final BigInteger maxPrime = primes.get(primes.size() - 1);

    List<BigInteger> factors(int n) {
        return factors(BigInteger.valueOf(n));
    }

    List<BigInteger> factors(BigInteger n) {
        List<BigInteger> factors = new ArrayList<>();
        for (BigInteger p : primes) {
            while (n.remainder(p).equals(BigInteger.ZERO)) {
                factors.add(p);
                n = n.divide(p);
            }
            if (n.compareTo(p) < 0) {
                break;
            }
        }
        if (n.compareTo(maxPrime) > 0) {
            factors.add(n);
        }
        return factors;
    }

    Optional<BigInteger> getProblem(List<BigInteger> factors) {
        BigInteger problem = factors.get(factors.size() - 1);
        if (problem.compareTo(maxPrime) <= 0) {
            return Optional.empty();
        }
        if (problem.isProbablePrime(20)) {
            return Optional.empty();
        }
        return Optional.of(problem);
    }
}
