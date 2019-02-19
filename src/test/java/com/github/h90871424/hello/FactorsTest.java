package com.github.h90871424.hello;

import com.gazman.factor.QuadraticThieve;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FactorsTest {

    private static final BigInteger NINE = BigInteger.valueOf(9);

    @Test
    void factors() {
        Factors f = new Factors();
        List<BigInteger> factors = f.factors(99);
        assertEquals(BigInteger.valueOf(99), product(factors));
    }

    @Test
    void testGermanSequence() {
        Factors f = new Factors();
        BigInteger start = NINE;
        for (int i = 1; i < 100; i++) {
            List<BigInteger> factors = f.factors(start);
            factors = solveRemaining(f, factors);
            assertEquals(start, product(factors));
            for (BigInteger factor : factors) {
                Assertions.assertTrue(factor.isProbablePrime(10));
            }
            System.out.printf("%d -> %s%n", start, factors);
            start = start.add(NINE.multiply(BigInteger.TEN.pow(i)));
        }
    }

    private List<BigInteger> solveRemaining(Factors f, List<BigInteger> factors) {
        Optional<BigInteger> opt = f.getProblem(factors);
        if (!opt.isPresent()) {
            return factors;
        }
        BigInteger problem = opt.get();
        BigInteger solution = new QuadraticThieve(problem).start();
        List<BigInteger> result = new ArrayList<>();
        for (int i = 0; i < factors.size() - 1; i++) {
            BigInteger factor = factors.get(i);
            result.add(factor);
        }
        result.add(solution);
        result.add(problem.divide(solution));
        return result;
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
        BigInteger solution = new QuadraticThieve(problem).start();
        Assertions.assertNotEquals(problem, solution);
        Assertions.assertNotEquals(BigInteger.ONE, solution);
        Assertions.assertEquals(BigInteger.ZERO, problem.mod(solution));
    }

    private static BigInteger product(List<BigInteger> factors) {
        BigInteger result = BigInteger.ONE;
        for (BigInteger factor : factors) {
            result = result.multiply(factor);
        }
        return result;
    }
}
