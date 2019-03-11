package com.github.h90871424.pollard;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;


class PollardRho {
    private final static BigInteger TWO = new BigInteger("2");
    private final static SecureRandom random = new SecureRandom();

    private static BigInteger rho(BigInteger N) {
        BigInteger divisor;
        BigInteger c = new BigInteger(N.bitLength(), random);
        BigInteger x = new BigInteger(N.bitLength(), random);
        BigInteger xx = x;

        // check divisibility by 2
        if (N.mod(TWO).equals(ZERO)) {
            return TWO;
        }

        long count = 0;
        int innerCount = 0;

        do {
            x = x.multiply(x).mod(N).add(c).mod(N);
            xx = xx.multiply(xx).mod(N).add(c).mod(N);
            xx = xx.multiply(xx).mod(N).add(c).mod(N);
            divisor = x.subtract(xx).gcd(N);
            if (count++ % 1048576 == 0) {
                innerCount++;
                System.out.printf(
                        "%s %d %s %s%n",
                        Thread.currentThread().getName(),
                        innerCount,
                        x,
                        xx);
            }
        } while (innerCount < 100 && divisor.equals(ONE));

        return divisor;
    }

    private static Optional<BigInteger> factor(BigInteger N) {
        if (N.equals(ONE)) {
            return Optional.empty();
        }
        if (N.isProbablePrime(20)) {
            return Optional.of(N);
        }
        BigInteger divisor = rho(N);
        factor(divisor);
        factor(N.divide(divisor));
        return Optional.empty();
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        BigInteger N = new BigInteger("92556179448994367391887834053878562534782033760810527051075248738484727059555245899601591");
        ExecutorService pool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 100; i++) {
            List<Future<Optional<BigInteger>>> futures = new ArrayList<>(4);
            futures.add(pool.submit(() -> {
                Thread.currentThread().setName("T1");
                return factor(N);
            }));
            futures.add(pool.submit(() -> {
                Thread.currentThread().setName("T2");
                return factor(N);
            }));
            futures.add(pool.submit(() -> {
                Thread.currentThread().setName("T3");
                return factor(N);
            }));
            for (Future<Optional<BigInteger>> future : futures) {
                Optional<BigInteger> result = future.get();
                if (result.isPresent()) {
                    System.out.println(result.get());
                    System.err.println(result.get());
                    System.out.flush();
                    System.err.flush();
                    return;
                }
            }
        }
    }
}
