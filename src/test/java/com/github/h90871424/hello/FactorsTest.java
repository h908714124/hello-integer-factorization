package com.github.h90871424.hello;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FactorsTest {

    @Test
    void factors() {
        List<Integer> factors = Factors.factors(99);
        assertEquals(BigInteger.valueOf(99), product(factors));
    }

    @Test
    void testGermanSequence() {
        long start = 9;
        for (int i = 1; i < 7; i++) {
            List<Integer> factors = Factors.factors((int) start);
            System.out.printf("%d -> %s%n", start, factors);
            start += 9 * Math.pow(10, i);
        }
    }

    private static BigInteger product(List<Integer> factors) {
        BigInteger result = BigInteger.ONE;
        for (Integer factor : factors) {
            result = result.multiply(BigInteger.valueOf(factor));
        }
        return result;
    }
}
