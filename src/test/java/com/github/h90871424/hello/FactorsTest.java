package com.github.h90871424.hello;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FactorsTest {

    private static final BigInteger NINE = BigInteger.valueOf(9);

    private static final Factors f = new Factors();

    @Test
    void factors() {
        List<BigInteger> factors = f.factors(99);
        assertEquals(BigInteger.valueOf(99), product(factors));
    }

    @Test
    void testGermanSequence() {
        BigInteger start = NINE;
        for (int i = 1; i < 100; i++) {
            List<BigInteger> factors = f.factors(start);
            System.out.printf("%d -> %s%n", start, factors);
            assertEquals(start, product(factors));
            start = start.add(NINE.multiply(BigInteger.TEN.pow(i)));
        }
    }

    @Test
    void testBla() {
        BigInteger start = BigInteger.valueOf(9999999999999L);
        System.out.println(f.factors(start));
        start = start.divide(BigInteger.valueOf(9));
        start = start.divide(BigInteger.valueOf(53));
        start = start.divide(BigInteger.valueOf(79));
        System.out.println(start);
        System.out.println(start.isProbablePrime(10));
    }

    private static BigInteger product(List<BigInteger> factors) {
        BigInteger result = BigInteger.ONE;
        for (BigInteger factor : factors) {
            result = result.multiply(factor);
        }
        return result;
    }
}
