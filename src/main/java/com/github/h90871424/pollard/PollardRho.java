package com.github.h90871424.pollard;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.math.BigInteger.ONE;


class PollardRho {

    // factor in 999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999
    private static final BigInteger N = new BigInteger("92556179448994367391887834053878562534782033760810527051075248738484727059555245899601591");

    private static BigInteger rho(BigInteger N) {
        BigInteger divisor;
        BigInteger x = new BigInteger(N.bitLength(), new SecureRandom());
        BigInteger y = x;

        int __count = 0, _count = 0;

        do {
            x = x.multiply(x).subtract(ONE).mod(N);
            y = y.multiply(y).subtract(ONE).mod(N);
            y = y.multiply(y).subtract(ONE).mod(N);
            divisor = x.subtract(y).gcd(N);
            if (__count++ == 1048576) {
                __count = 0;
                System.out.printf(
                        "%s %d %s %s%n",
                        Thread.currentThread().getName(),
                        _count++, x, y);
            }
        } while (_count < 100 && divisor.equals(ONE));

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
        int threads = Arrays.stream(args).mapToInt(Integer::parseInt).findFirst().orElse(3);
        if (N.isProbablePrime(10)) {
            throw new IllegalStateException("could be prime");
        }
        System.out.printf("using %d threads%n", threads);
        ExecutorService pool = Executors.newFixedThreadPool(threads);
        for (int i = 0; i < 100; i++) {
            List<Future<Optional<BigInteger>>> futures = new ArrayList<>(threads);
            for (int j = 0; j < threads; j++) {
                String threadName = "T" + i;
                futures.add(pool.submit(() -> {
                    Thread.currentThread().setName(threadName);
                    return factor(N);
                }));
            }
            for (Future<Optional<BigInteger>> future : futures) {
                Optional<BigInteger> result = future.get();
                if (result.isPresent()) {
                    // party mode
                    System.out.println();
                    System.out.println("##### A FACTOR WAS FOUND #####");
                    System.out.println(result.get());
                    System.err.println(result.get());
                    System.out.println(result.get());
                    System.out.println("##### A FACTOR WAS FOUND #####");
                    System.out.flush();
                    System.err.flush();
                    return;
                }
            }
        }
    }
}
