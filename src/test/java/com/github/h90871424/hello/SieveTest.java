package com.github.h90871424.hello;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SieveTest {

    @Test
    void testSieve() {
        int[] sieve = Sieve.create(100);
        for (int i : sieve) {
            BigInteger p = BigInteger.valueOf(i);
            assertTrue(p.isProbablePrime(100));
        }
        assertEquals(25, sieve.length);
    }

    @Test
    void bla() {
        int[] ints = Sieve.create((Integer.MAX_VALUE - 10) / 2);
        System.out.println(ints.length);
    }

    @Test
    void asdf() {
        System.out.println(BigInteger.valueOf(1416 - 311).gcd(BigInteger.valueOf(2041)));
        System.out.println(BigInteger.valueOf(1416 + 311).gcd(BigInteger.valueOf(2041)));
    }
}
