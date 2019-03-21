package com.github.h90871424.hello;

import com.github.h90871424.lenstra.FactorFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class FactorsTest {

    private static final BigInteger N = new BigInteger("92556179448994367391887834053878562534782033760810527051075248738484727059555245899601591");

    @Test
    void testSolution() {
        assertEquals(BigInteger.ZERO, N.divideAndRemainder(new BigInteger("846035731396919233767211537899097169"))[1]);
    }

    @Test
    void problematic() {
        String ones = "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
        assertEquals(97, ones.length());
        assertEquals(
                new BigInteger(ones),
                N.multiply(BigInteger.valueOf(12004721)));
        assertFalse(N.isProbablePrime(10));
    }

    @Test
    void factors() {
        Factors f = new Factors();
        List<BigInteger> factors = f.factors(99);
        assertEquals(BigInteger.valueOf(99), product(factors));
    }

    @Test
    void testGermanSequence() {
        Factors f = new Factors();
        for (int i = 100; i <= 110; i++) {
            BigInteger start = TEN.pow(i).subtract(ONE);
            List<BigInteger> factors = f.factors(start);
            assertEquals(start, product(factors));
            boolean hasProblem = false;
            for (BigInteger factor : factors) {
                boolean probablePrime = factor.isProbablePrime(50);
                if (!probablePrime) {
                    hasProblem = true;
                }
            }
            System.out.printf("%d | %s%s%n", i, factors.stream().map(BigInteger::toString).collect(Collectors.joining(", ")), hasProblem ? "*" : "");
        }
    }

    @Test
    void testProblem() {
        BigInteger problem = new BigInteger("4203852214522105994074156592890477");
        BigInteger solution = FactorFinder.findFactor(problem);
        System.out.printf("%s%n", solution);
    }

    @Test
    void testBla() {
        Factors f = new Factors();
        BigInteger start = BigInteger.valueOf(9999999999999L);
        System.out.println(f.factors(start));
        start = start.divide(BigInteger.valueOf(9));
        start = start.divide(BigInteger.valueOf(53));
        start = start.divide(BigInteger.valueOf(79));
        System.out.println(start);
        System.out.println(start.isProbablePrime(10));
    }

    @Test
    void testBiggie() {
        BigInteger problem = new BigInteger("341233306557836423189042926585457900151074303303755301");
        BigInteger solution = FactorFinder.findFactor(problem);
        Assertions.assertNotEquals(problem, solution);
        Assertions.assertNotEquals(ONE, solution);
        assertEquals(BigInteger.ZERO, problem.mod(solution));
    }


    static BigInteger product(List<BigInteger> factors) {
        BigInteger result = ONE;
        for (BigInteger factor : factors) {
            result = result.multiply(factor);
        }
        return result;
    }
}
