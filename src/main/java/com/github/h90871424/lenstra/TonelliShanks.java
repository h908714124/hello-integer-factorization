package com.github.h90871424.lenstra;

import java.math.BigInteger;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import static java.math.BigInteger.ZERO;

public class TonelliShanks {


    private static final BigInteger TWO = BigInteger.valueOf(2);
    private static final BigInteger THREE = BigInteger.valueOf(3);
    private static final BigInteger MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE / 2);

    private static final Solution NO_SOLUTION = new Solution(ZERO, false);

    private static class Solution {
        private final BigInteger root;
        private final boolean exists;

        Solution(BigInteger root, boolean exists) {
            this.root = root;
            this.exists = exists;
        }
    }


    public static class XYSolution {

        private final BigInteger x;
        private final BigInteger y;

        XYSolution(BigInteger x, BigInteger y) {
            this.x = x;
            this.y = y;
        }

        public BigInteger getX() {
            return x;
        }

        public BigInteger getY() {
            return y;
        }
    }


    public static Optional<XYSolution> findPoint(BigInteger n, BigInteger a, BigInteger b) {
        BigInteger x = new BigInteger(10, ThreadLocalRandom.current());
        Solution solution;
        BigInteger rhs;
        int count = 100;
        do {
            x = x.add(BigInteger.ONE);
            rhs = x.modPow(THREE, n).add(a.multiply(x)).add(b).mod(n);
            solution = ts(rhs);
        }
        while (count-- > 0 && !solution.exists);
        if (!solution.exists) {
            return Optional.empty();
        }
        checkSolution(n, x, solution.root, a, b);
        return Optional.of(new XYSolution(x, solution.root));
    }

    private static void checkSolution(BigInteger n, BigInteger x, BigInteger y, BigInteger a, BigInteger b) {
        BigInteger lhs = y.modPow(TWO, n);
        BigInteger rhs = x.modPow(THREE, n).add(x.multiply(a)).add(b).mod(n);
        if (!lhs.equals(rhs)) {
            throw new IllegalStateException("" + lhs + " != " + rhs);
        }
    }

    private static Optional<Solution> maybeSolve(BigInteger rhs) {
        if (rhs.compareTo(MAX_LONG) > 0) {
            return Optional.empty();
        }
        double sqrt = Math.sqrt(rhs.longValueExact());
        if (sqrt != Math.floor(sqrt)) {
            return Optional.empty();
        }
        return Optional.of(new Solution(BigInteger.valueOf(Math.toIntExact((long) sqrt)), true));
    }


    private static Solution ts(BigInteger rhs) {
        return maybeSolve(rhs).orElse(NO_SOLUTION);
    }
}
