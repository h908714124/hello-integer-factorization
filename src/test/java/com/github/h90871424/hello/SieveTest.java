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
}
